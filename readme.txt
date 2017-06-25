将本地包放入到maven中步骤：
1. cd 到目标jar包目录
2. mvn install:install-file -Dfile=learner-util.jar 
	-DgroupId=cn.learner.util -DartifactId=util -Dversion=1.0 -Dpackaging=jar
3. <dependency>
			<groupId>cn.learner.util</groupId>
			<artifactId>util</artifactId>
			<version>1.0</version>
		</dependency>