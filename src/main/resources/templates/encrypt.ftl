<#assign base=ctx.contextPath />
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>密码加密</title>
    <script src="${base}/js/encrypt.js" charset="UTF-8"></script>
    <script>
         document.write($.Encryption.getEncryption(atob('${pcvk.pwd}'), ${pcvk.hexCode},${pcvk.verifyCode}, undefined));
    </script>
</head>
<body>

</body>

</html>