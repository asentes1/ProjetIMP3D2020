<%-- 
    Document   : Page_de_test
    Created on : 31 mars 2020, 07:49:36
    Author     : Lorenzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inscription d'un utilisateur</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="http://localhost:8084/imp3Dgit/test_envoi_mail.jsp"></script>
        <script type="text/javascript" src="test_envoi_mail.jsp"></script>
        <script>
            //que des cars alphanumérique 
            $.validator.addMethod("textOnly", 
                function(value, element) {  // un car n'est pas alphanumérique
                    return !/[^a-zA-Z0-9]/.test(value);
                }, "Que des caractères alphanumériques."
            );
        </script>   
    </head>
    <body>
        <header>
            <div id="titre" class="info">Projet de supervision imp 3D</div>
        </header>
        <section>
            Saisie du mail d'un client
            <form method="get" action="test_envoi_mail.jsp">
                <div data-role="fieldcontain">
                <label for="inscriptionEMail" data-theme="a">EMail:</label>
                <input type="email" name="inscriptionEMail" id="inscriptionEMail"
                      data-theme="a" placeholder="Votre EMail" /> 
                </div>
                <a herf="#test_envoi_mail.jsp"><button>Valider</button></a>
            </form>
        </section>
        <footer> 
            <div id= "pied" class="info">BTS SNIR - 2020</div>
        </footer>
    </body>
</html>
