package com.example.springweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class MainController
{

    @Autowired
    CredentialRepository credentialRepository;
    @Autowired
    UsertypelinkRepository usertypelinkRepository;
    @Autowired
    UserdetailRepository userdetailRepository;



    @GetMapping("/")
    public String getLandingPage(){
        return "landingpage";
    }

    @GetMapping("/save")

    public String saveCredendials()
    {
        Credential cr = new Credential();
        cr.setUsername("abc");
        cr.setPassword("abc@123");
        credentialRepository.save(cr);
        return "New Credential Saved";

    }

    @PostMapping("/signup")
    public String signup (@RequestParam("username") String username,
                          @RequestParam("password") String password)
    {
        Credential credential = new Credential();
        credential.setUsername(username);
        credential.setPassword(password);

        credentialRepository.save(credential);

        return "dashboardpage";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password ,
                        HttpSession session , Model model)
    {
        Optional<Credential> matchedCredental = credentialRepository.findById(username);

        if (matchedCredental.isPresent())
        {
            if (matchedCredental.get().getPassword().equals(password))
            {
                session.setAttribute("username", username);
                Optional<Userdetail> userdetail = userdetailRepository.findById(username);
                List<Usertypelink> usertypelinks = usertypelinkRepository.findAll();
                Optional<Usertypelink> usertypelink = usertypelinks.stream().
                        filter(usertypelink1 -> usertypelink1.
                                getUsername().equals(username)).findAny() ;

                if(userdetail.isPresent())
                {
                    if(usertypelink.isPresent())
                    {
                        if(usertypelink.equals("Buyer")){
                            return "buyerdashboard";
                        }
                        else if(usertypelink.equals("Seller"))
                        {
                            return "sellerdashboard";
                        }
                    }
                    else
                    {
                        return "interimdashboard";
                    }
                }
                else
                {
                    return "interimdashboard";
                }
            }
        }
        else
        {
            return "landingpage";
        }
    return "landingpage";}



    @PostMapping("userdetail")
    public String userdetail(@RequestParam("username") String username ,
                             @RequestParam("fname") String fname,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam("lname") String lname ,
                             HttpSession session,
                             Model model)
    {
        Userdetail detail = new Userdetail();
        detail.setUsername((String)session.getAttribute("username"));
        detail.setFname(fname);
        detail.setEmail(email);
        detail.setPhone(phone);
        detail.setLname(lname);

        userdetailRepository.save(detail);

        return "welcome";

    }
}

