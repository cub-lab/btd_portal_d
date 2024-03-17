<html xmlns="http://www.w3.org/TR/REC-html40">
    <head>
        <meta http-equiv=Content-Type content="text/html; charset=unicode">
        <meta name=viewport
              content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <title>Account activation</title>
        <style>
            a:link, a:visited {
                background-color: #04AA6D !important;
                color: white;
                padding: 14px 25px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
            }

            a:hover, a:active {
                background-color: #059862;
            }
        </style>
    </head>
    <body>
        <h3>Dear ${firstName} ${lastName},</h3>
		<div style="margin: 10px; font-family: sans-serif">
			<p>
				To verify your email address and activate your account please click on the following link (the link is active for 24 hours):
			</p>
			<a href="${activationLink}" target="_blank" class="activation-btn">Activate now</a><br/>
			<p>
				Or copy the activation link: ${activationLink}
			</p>
			<br/>
		</div>	
		<p>Cu drag,</p>
		<strong>Echipa BTDConstruct & Ambient</strong>
    </body>

</html>