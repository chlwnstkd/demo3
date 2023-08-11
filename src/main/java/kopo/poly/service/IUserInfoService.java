package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;

public interface IUserInfoService {
    int insertUserInfo(UserInfoDTO pDTO) throws Exception;
    UserInfoDTO getLogin(UserInfoDTO pDTO) throws Exception;

    UserInfoDTO getUserIdExists(UserInfoDTO pDTO) throws Exception;

    UserInfoDTO getEmailExists(UserInfoDTO pDTO) throws Exception;
//    UserInfoDTO searchUserIdOrPasswordPrac(UserInfoDTO pDTO) throws Exception;
//    int newPasswordProc(UserInfoDTO pDTO) throws Exception;
 }
