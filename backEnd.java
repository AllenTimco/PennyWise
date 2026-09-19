import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows HTML to talk to Java during testing
public class BankController {

    @GetMapping("/token")
    public String getToken() {
        // In a real app, you would call Plaid here and get a real token
        return "{\"link_token\": \"link-sandbox-12345\"}";
    }
}