<#if !MV.onlyUseModule>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${MV.browserTitle?if_exists}</title>

</head>
<body>
    <#attempt>
<#if !MV.disableLayout && MV.layoutFile??>
        <#include MV.layoutFile>
    <#else>
        <#include MV.templateFile>
    </#if>
<#recover>
    </#attempt>
</body>
</html>
<#else>
    <#attempt>
        <#include MV.templateFile>
        <#recover>
    </#attempt>
</#if>