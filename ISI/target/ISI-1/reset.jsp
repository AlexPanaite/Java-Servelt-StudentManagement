<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <style type="text/css">
        body{ font: 14px sans-serif; }
        .wrapper{ width: 350px; padding: 20px; margin-left:40% ; margin-top:5%}
        #send{
            margin-top: 10px;
            margin-right: 165px;
        }
        #cancel{
            margin-top: 10px;
        }
        #ps{
            margin-top: 10px;
            margin-bottom: 10px;
        }
        #cp{
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <h2>Reset Password</h2>
    <p>Please fill out this form to reset your password.</p>
    <form>
    <div>
        <label>New Password</label>
        <input id="ps" type="password" name="new_password" class="form-control" onkeyup='check();'>
    </div>
    <div>
        <label>Confirm Password</label>
        <input id="cp" type="password" name="confirm_password" class="form-control" onkeyup='check();'>
        <span id='message'></span>
    </div>
    <div class="form-group">
        <button formaction="login/change" id ="send" type="submit" class="btn btn-primary" >Submit</button>
        <button formaction="index.jsp" id="cancel" type="submit" class="btn btn-danger" >Cancel</button>
    </div>
    </form>
</div>
</body>

<script>
    var check = function() {
        if (document.getElementById('ps').value ==
            document.getElementById('cp').value) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'matching';
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerHTML = 'not matching';
        }
    }
</script>
</html>