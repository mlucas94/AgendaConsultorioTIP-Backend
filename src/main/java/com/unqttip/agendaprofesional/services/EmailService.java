package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.model.TipoDeTurno;
import com.unqttip.agendaprofesional.model.Turno;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    private Properties props = new Properties();
    private final Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("agendate.app.noreply@gmail.com", "teuryexmwcqaduhv");
                }
            });

    private final String TEMPLATE_REGULAR = "<p>Hola, <b>%s</b>. </p>" +
            "<p>Le informamos que su turno ha sido registrado correctamente para el día <b>%s</b> desde las <b>%s hs</b> hasta las <b>%s hs</b>. <br>" +
            "Este es un mensaje automático, ante cualquier duda consulte con el profesional asignado. </p>" +
            "<p>¡Saludos! <i>agendate APP</i></p>";
    private final String TEMPLATE_SOBRETURNO = "<p>Hola, <b>%s</b>. </p>" +
            "<p>Le informamos que su sobreturno ha sido registrado correctamente para el día <b>%s</b> desde las <b>%s hs</b> hasta las <b>%s hs</b>. <br>" +
            "Recuerde que por ser un sobreturno es posible que deba esperar más allá de la hora asignada. <br>" +
            "Este es un mensaje automático, ante cualquier duda consulte con el profesional asignado. </p>" +
            "<p>¡Saludos! <i>agendate APP</i></p>";
    private final String TEMPLATE_PRIORITARIO = "<p>Hola, <b>%s</b>. </p>" +
            "<p>Le informamos que su turno ha sido registrado correctamente para el día <b>%s</b> desde las <b>%s hs</b> hasta las <b>%s hs</b>. <br>" +
            "Este es un mensaje automático, ante cualquier duda consulte con el profesional asignado. </p>" +
            "<p>¡Saludos! <i>agendate APP</i></p>";

    public void enviarMailNuevoTurno(Turno turno) {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String cuerpoEmail = "";
        if (turno.getTipo() == TipoDeTurno.REGULAR) {
            cuerpoEmail = TEMPLATE_REGULAR;
        } else if (turno.getTipo() == TipoDeTurno.SOBRETURNO) {
            cuerpoEmail = TEMPLATE_SOBRETURNO;
        } else if (turno.getTipo() == TipoDeTurno.PRIORITARIO) {
            cuerpoEmail = TEMPLATE_PRIORITARIO;
        }
        cuerpoEmail = String.format(
                cuerpoEmail,
                turno.getPaciente().getNombre(),
                turno.getHorarioInicio().toLocalDate().toString(),
                turno.getHorarioInicio().toLocalTime().toString(),
                turno.getHorarioFin().toLocalTime().toString()
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("agendate.app.noreply@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("nahueliglesias1999@gmail.com")); //TODO: turno.getPaciente().getEmail();
            message.setSubject("agendate APP: nuevo turno asignado");
            message.setContent(cuerpoEmail, "text/html");

            Transport.send(message);

            System.out.println("Correo electrónico enviado");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
