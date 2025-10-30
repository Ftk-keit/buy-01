FirstRepos="origin"
SecondRepos="github"

BRANCH="main"

git add .


echo "Entrez le message de commit : "
read COMMIT_MSG


git commit -m "$COMMIT_MSG"


git push $FirstRepos $BRANCH
git push $SecondRepos $BRANCH