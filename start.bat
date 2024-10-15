start hahashop-page\dist\index.html

cd hahashop-serve

IF NOT EXIST target\test.db (
    copy test.db target\
)

cd target

java -Dfile.encoding=GBK -jar hahashop-server-0.0.1-SNAPSHOT.jar
