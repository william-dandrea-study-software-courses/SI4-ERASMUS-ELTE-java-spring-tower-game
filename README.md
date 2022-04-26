# ⚠️ If you cannot launch the project

- Leave intellij
- Delete the .idea and the .mvn folder
- Launch again intellj


# Project The Knight

Goal is to create a tower defence game in Java

# How to manage GitLab

Steps for managing Gitlab

### How to commit on GitLab

- #### Step 1 :
Code : 
```
git add .
git pull
```
Or in IntelliJ
![](ressources/git_pull_illustration.png)

=> If merge trouble, correct / adapt your code
- #### Step 2 : 
```
mvn clean
mvn package
mvn test
```

If the build fail, please correct the problems
![](ressources/build_success.png)


- #### Step 3 : commit with your message
Code :
```
git commit -m "Your message"
```
Or in intellij
![](ressources/git_commit_illustration.png)

![](ressources/git_comit_message_illustration.png)

- #### Step 4 : push your commit
```
git push
```

Or in intellij
![](ressources/git_push.png)
