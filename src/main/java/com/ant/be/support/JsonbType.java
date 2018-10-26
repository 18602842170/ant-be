package com.ant.be.support;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonbType implements UserType{
	private final ObjectMapper mapper = new ObjectMapper();;

	/**
	当Hibernate加载数据时会调用该方法，该方法会从底层JDBC ResultSet读取数据，将其
	转化为自定义类型后返回，该方法要求对可能出现的null值作处理，names中包含了当前自定义类型的映射字段名称。
	 **/
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		PGobject o = (PGobject) rs.getObject(names[0]);
		if(null != o) {
			if (null != o.getValue()) {
				//                return mapper.readValue(o.getValue(),String.class);
				return conversToString(o.getValue());
			}
			
		}
        return new String();
	}
	
	public String conversToString(Object obj) {
		String str =obj.toString().substring(1, obj.toString().length()-1).replace("\\", "");
		return str;
	}

	/**
	   本方法将在Hibernate进行数据库保存时被调用我们可以通过PrepareStatement将自定义数据
	   写入数据库
	 **/
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            try{
                st.setObject(index, mapper.writeValueAsString(value), Types.OTHER);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
		
	}

    @Override
    public Object deepCopy(Object originalValue) throws HibernateException {
        if (originalValue != null) {
            try {
                return mapper.readValue(mapper.writeValueAsString(originalValue),
                        returnedClass());
            } catch (IOException e) {
                throw new HibernateException("Failed to deep copy object", e);
            }
        }
        return null;
    }


    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        Object copy = deepCopy(value);

        if (copy instanceof Serializable) {
            return (Serializable) copy;
        }

        throw new SerializationException(String.format("Cannot serialize '%s', %s is not Serializable.", value, value.getClass()), null);
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        if (x == null) {
            return 0;
        }

        return x.hashCode();
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return ObjectUtils.nullSafeEquals(x, y);
    }

    @Override
    public Class<?> returnedClass() {
        return String.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

	
}
