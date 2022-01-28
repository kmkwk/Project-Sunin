package com.ssafy.sunin.user;

import com.ssafy.sunin.domain.user.CompanyUser;
import com.ssafy.sunin.domain.user.Role;
import com.ssafy.sunin.repository.CompanyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyUserService {

    private final CompanyUserRepository companyUserRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(CompanyUserRequest request){
        companyUserRepository.save(CompanyUser.builder()
                .companyId(request.getCompanyId())
                .companyPassword(request.getCompanyPassword())
                .companyName(request.getCompanyName())
                .companyNickname(request.getCompanyNickname())
                .companyTel(request.getCompanyTel())
                .companyAddress(request.getCompanyAddress())
                .role(Role.USER)
                .build());
        return "Success";
    }

    public CompanyUser login(String companyId, String companyPassword){
        Optional<CompanyUser> userfind = companyUserRepository.findByCompanyId(companyId);
        if(userfind.get().getCompanyPassword().equals(companyPassword)){
            CompanyUser companyUser = companyUserRepository.findByCompanyId(companyId).orElseThrow();
            return companyUser;
        }
        return null;
    }

    public String deleteCompanyUser(String companyId){
        companyUserRepository.delete(companyUserRepository.findByCompanyId(companyId).orElseThrow(RuntimeException::new));
        return "Success";
    }

    public List<CompanyUser> listCompanyUser() throws Exception{
        return companyUserRepository.findAll();
    }

    public CompanyUser detailCompanyUser(String companyId){
        CompanyUser companyUser = companyUserRepository.findByCompanyId(companyId).orElseThrow();
        return companyUser;
    }

    public String updateCompanyUser(CompanyUserRequest request){
        Optional<CompanyUser> companyUser = companyUserRepository.findByCompanyId(request.getCompanyId());
        companyUser.ifPresent(selectCompanyUser->{
            selectCompanyUser.setCompanyPassword(request.getCompanyPassword());
            selectCompanyUser.setCompanyName(request.getCompanyName());
            selectCompanyUser.setCompanyNickname(request.getCompanyNickname());
            selectCompanyUser.setCompanyAddress(request.getCompanyAddress());
            selectCompanyUser.setCompanyTel(request.getCompanyTel());
            selectCompanyUser.setApproval(request.isApproval());
            companyUserRepository.save(selectCompanyUser);
        });
        return "Success";
    }

}
