package com.spots.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.UserInfo;


@Mapper
public interface UserMapper {

	/**
	 * 로그인
	 * 
	 * @param id,pw // 아이디, 비밀번호 입력받음
	 * @return UserInfo 리턴
	 */
	public UserInfo login(@Param("email") String email,@Param("password") String password);

	/**
	 * 회원가입
	 * 
	 * @param UserInfo //회원정보 입력 후 가입
	 * @return 성공/실패
	 */

	public int signUp(UserInfo user);

	/**
	 * 정보수정
	 * 
	 * @param UserInfo,id // 현재 사용자의 id로 userinfo를 가져와서 수정
	 * @return 성공/실패
	 */

	public int modify(UserInfo user);


	/**
	 * 아이디로 비밀번호 찾기
	 * 
	 * @param id //id를 주면 비밀번호 찾아줌
	 */

	public UserInfo findPwdByEmail(String email);
	
	
	public UserInfo detail(String email);

	public int joinTeam(@Param("uid") int uid, @Param("team_uid") int team_uid);

	public int outTeam(@Param("uid") int uid, @Param("team_uid") int team_uid);


	public int applyTeam(ApplyTeamInfo dto);

	public UserInfo detail2(int uid);

	public int rejectTeam(@Param("uid") int uid, @Param("team_uid") int team_uid);



}
