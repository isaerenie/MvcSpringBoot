package com.example.services;

import com.example.entities.Admin;
import com.example.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class AdminServices {
    final AdminRepository adminRepository;
final HttpServletRequest req;
final HttpServletResponse res;
    public AdminServices(AdminRepository adminRepository, HttpServletRequest req, HttpServletResponse res) {
        this.adminRepository = adminRepository;
        this.req = req;
        this.res = res;
    }

    public String login(Admin admin) {
        Optional<Admin> adminOpAnd = adminRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(admin.getEmail(), admin.getPassword());
        if (adminOpAnd.isPresent()) {
            Admin adm=adminOpAnd.get();
            req.getSession().setAttribute("admin",adm);
            return "redirect:/dashboard";
        }else {
            return "redirect:/";
        }
    }

}
