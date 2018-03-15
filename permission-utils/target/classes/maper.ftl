<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.${projectName}.dao.${pojoName}Dao">
 
	<!-- 添加用户 -->
	<insert id="add" parameterType="${pojoName}" 
	useGeneratedKeys="true" keyColumn="id" keyProperty="id">
		INSERT INTO ${table_name} (
		<#list columnNames as name>${name}<#if name_has_next>,</#if></#list>
		) 
		VALUES(<#list columnNames as name>${jing}{${name}}<#if name_has_next></#if></#list>)
	</insert> 
	<!-- 批量删除 -->
	<delete id="deleteByIds" parameterType="java.lang.reflect.Array">
		delete from ${table_name} where id in 
		<foreach collection="array" item="id" open="(" close=")" separator=",">
			${jing}{id}
		</foreach> 
	</delete> 
	<!-- 封装条件 -->
	<sql id="conditionSql">
		<if test="condition != null">
			<where>
				 
			</where>
		</if>
	</sql> 
	<select id="getById" resultMap="userMap" parameterType="int">
		SELECT <#list columnNames as name>${name}<#if name_has_next>,</#if></#list>  FROM ${table_name} WHERE id = ${jing}{id}
	</select>
	
	 
	<!-- 查询用户 -->
	<select id="getListByCondition" resultMap="userMap" >
		SELECT <#list columnNames as name>${name}<#if name_has_next>,</#if></#list> FROM ${table_name} 
		<include refid="conditionSql"></include>
		order by ${dao}{column} ${dao}{orderBy}
		limit #{start},#{limit}
	</select>
	
	<!-- 修改用户 -->
	<update id="update" parameterType="${pojoName}"> 
		UPDATE ${table_name} 
		<#list columnNames as name>${name} = ${jing}{${name}} <#if name_has_next>,</#if></#list>
		WHERE id = #{id}
	</update>
	
	<!-- 查询总条数 -->
	<select id="getCount" resultType="int"> 
		select count(1) from ${table_name}
		<include refid="conditionSql"></include>
	</select>
	 
</mapper>