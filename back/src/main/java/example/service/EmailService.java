package example.service;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String recipientEmail, String subject, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

            messageHelper.setFrom("uheisenberg@mail.ru"); // Замените на ваш адрес электронной почты Mail.ru
            messageHelper.setTo(recipientEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            javaMailSender.send(mimeMessage);

        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
