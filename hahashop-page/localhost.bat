
call npm run localhost

rd /s /q ..\hahashop-serve\src\main\resources\static\fonts
rd /s /q ..\hahashop-serve\src\main\resources\static\js
rd /s /q ..\hahashop-serve\src\main\resources\static\index.html
rd /s /q ..\hahashop-serve\src\main\resources\static\favicon.ico
xcopy /e /y dist ..\hahashop-serve\src\main\resources\static

cd ..
cd hahashop-serve

copy /y src\main\resources\applicationymls\localhost.yml src\main\resources\application.yml

call mvn clean
call mvn package



