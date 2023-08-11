package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserInfoMapper;
import kopo.poly.service.IMailService;
import kopo.poly.service.IUserInfoService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoService implements IUserInfoService {
    private final IUserInfoMapper userInfoMapper;

    private final IMailService mailService;

    @Override
    public UserInfoDTO getUserIdExists(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getUserIdExists Start!");

        UserInfoDTO rDTO = userInfoMapper.getUserIdExists(pDTO);

        log.info(this.getClass().getName() + ".getUserIdExists End!");

        return rDTO;
    }

    @Override
    public UserInfoDTO getEmailExists(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".emailAuth Start!");
        log.info("pdto email : " + pDTO.getEmail());

        UserInfoDTO rDTO = userInfoMapper.getEmailExists(pDTO);
        if(rDTO == null) {
            rDTO = new UserInfoDTO();
        }
        String exists_yn = CmmUtil.nvl(rDTO.getExists_yn());

        log.info("exists_yn : " + exists_yn);

        if(exists_yn.equals("N")) {

            int authNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);

            log.info("authNumber : " + authNumber);

            MailDTO dto = new MailDTO();

            dto.setTitle("이메일 중복 확인 인증번호 발송 메일");
            dto.setContents("인증번호는 " + authNumber + " 입니다.");
            dto.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

            mailService.doSendMail(dto);

            dto = null;

            rDTO.setAuthNumber(authNumber);
        }

        log.info(this.getClass().getName() + ".emailAuth End!");

        return rDTO;
    }

    @Override
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertUserInfo Start!");

        int res = 0;

        res = userInfoMapper.insertUserInfo(pDTO);

//        Optional<UserInfoDTO> rDTO = Optional.ofNullable(
//                userInfoMapper.getUserId(pDTO));
//
//        int res = 0;
//        if(!rDTO.isPresent()) {
//            res = userInfoMapper.insertUserInfo(pDTO);
//        } else {
//            res = 2;
//        }

        log.info(this.getClass().getName() + ".insertUserInfo End!");

        return res;
    }

    @Override
    public UserInfoDTO getLogin(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getLogin Start!");
        UserInfoDTO rDTO = Optional.ofNullable(userInfoMapper.getLogin(pDTO)).orElseGet(UserInfoDTO::new);
        //위 코딩은 코드가 줄어들지만 가독성이 떨어진다
        //그래서 가독성을 위해 아래와 같이 코딩할 수도 있다
        //UserInfoDTO rDTO = userInfoMapper.getLogin(pDTO);
        //if(rDTO == null)
        //  rDTO = new UserInfoDTO();
        if (CmmUtil.nvl(rDTO.getUser_id()).length() > 0) {
            log.info("로그인 성공");
        }
        //Cmmutil.nvl은 들어온 값이 null이면 빈 문장으로 바꾼다. 아마도 ""로 바뀌는 듯
        //그래서 길이도 0
        log.info(this.getClass().getName() + ".getLogin End!");
        return rDTO;
    }

}
