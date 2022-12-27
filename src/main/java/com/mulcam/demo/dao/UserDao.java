package com.mulcam.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mulcam.demo.entity.User;

@Mapper
public interface UserDao {

	@Select("select * from users")
	List<User> getList();

	@Select("select * from users where uid=#{uid}")
	User get(String uid);
	
	@Insert("insert into users values(#{uid}, #{pwd}, #{uname}, #{email}, default, default)")
	void insert(User u);
	
	@Update("update users set uname=#{uname}, email=#{email} where uid=#{uid}")
	void update(User u);
	
//	@Delete("delete from users where uid=#{uid}")
//	void delete(String uid);
	
	@Update("update users set isDeleted=1 where uid=#{uid}")
	void delete(String uid);
}
