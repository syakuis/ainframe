<#import "functions.ftl" as funs>
<#assign css_bundle>
    <link rel="stylesheet" href="<@spring.url "/resources/dist/bundle.css"/>"/>
</#assign>
<#assign js_bundle>
    <script src="<@spring.url "/resources/dist/bundle.js" />"></script>
</#assign>
<#if !MV.onlyUseModule>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${MV.browserTitle?if_exists}</title>
    ${css_bundle}
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

    ${js_bundle}
</body>
</html>
<#else>
    <#attempt>
        <#include MV.templateFile>
        <#recover>
    </#attempt>
</#if>