<html xmlns="http://www.w3.org/TR/REC-html40">
    <head>
        <meta http-equiv=Content-Type content="text/html; charset=unicode">
        <meta name=viewport
              content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <title>Account activation</title>
    </head>
    <body>
        <h3>Dear ${firstName} ${lastName},</h3>
		<div style="margin: 10px; font-family: sans-serif">
			<p>To verify your email address and activate your account please click on the following link (the link is active for 24 hours):</p>
			<a style="display:inline-block;background:#5865f2;color:white;font-family:Poppins,Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif!important;font-size:13px;font-weight:600;line-height:16px;margin:0;text-decoration:none;text-transform:none;padding:8px 18px;border-radius:30px"
			   href="${activationLink}"
			   target="_blank">Activate now</a><br/>
			<p>Or copy the activation link: ${activationLink}</p>
			<br/>
		</div>	
		<p>Sincerely,</p>
		<strong>BTDConstruct & Ambient team</strong>
    </body>

</html>