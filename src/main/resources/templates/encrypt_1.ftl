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
        document.write($.Encryption.getEncryption("123abc123abc", '\x00\x00\x00\x00\x7d\x86\x50\xf9','!VSI', undefined).length+btoa("\x00\x00\x00\x00\x7d\x86\x50\xf9"));
    </script>
</head>
<body>

</body>

</html>