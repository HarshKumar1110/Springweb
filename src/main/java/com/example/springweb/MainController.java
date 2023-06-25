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
    @Autowired
    ProductofferRepository productofferRepository;


    @GetMapping("/")
    public String getLandingPage(){
        return "landingpage";
    }


    @PostMapping("/signup")
    public String signup (@RequestParam("username") String username,
                          @RequestParam("password") String password)
    {
        Credential credential = new Credential();
        credential.setUsername(username);
        credential.setPassword(password);

        credentialRepository.save(credential);

        return "interimdashboard";
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
                Optional<Usertypelink> usertypelink = usertypelinks.stream().filter(usertypelink1 -> usertypelink1.
                                getUsername().equals(username)).findAny() ;

                if(userdetail.isPresent())
                {
                    if(usertypelink.isPresent())
                    {
                        if(usertypelink.get().getType().equals("Buyer"))
                        {
                            model.addAttribute(productofferRepository);
                            return "buyerdashboard";
                        }
                        else if(usertypelink.get().getType().equals("Seller"))
                        {
                            return "sellerdashboard";
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



    @PostMapping("details")
    public String userdetail(@RequestParam("fname") String fname,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam("lname") String lname ,
                             @RequestParam("type") String type,
                             HttpSession session,
                             Model model)
    {
        Userdetail details = new Userdetail();

        details.setUsername((String)session.getAttribute("username"));
        details.setFname(fname);
        details.setEmail(email);
        details.setPhone(phone);
        details.setLname(lname);
        details.setType(type);

        userdetailRepository.save(details);
        model.addAttribute(details);

        return "welcome";

    }
}

