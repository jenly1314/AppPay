#!/bin/bash

set -ex

# Generate the API docs
./gradlew dokkaHtmlMultiModule

mkdir -p docs/api
mv build/dokka/htmlMultiModule/* docs/api

# Copy in special files that GitHub wants in the project root.
GITHUB_URL=https://github.com/jenly1314/AppPay/
echo $GITHUB_URL
sed "/<!-- end -->/q" README.md > docs/index.md
sed -i "s|app/src/main/ic_launcher-web.png|ic_logo.png|g" docs/index.md
sed -i "s|](app|](${GITHUB_URL}blob/master/app|g" docs/index.md
sed -i "s|](doc|](${GITHUB_URL}blob/master/doc|g" docs/index.md
sed -i "s|](alipay|](${GITHUB_URL}blob/master/alipay|g" docs/index.md
sed -i "s|](wxpay|](${GITHUB_URL}blob/master/wxpay|g" docs/index.md
sed -i "s|](unionpay|](${GITHUB_URL}blob/master/unionpay|g" docs/index.md
cat CHANGELOG.md | grep -v '## 版本日志' > docs/changelog.md

cp -r art docs/art
cp app/src/main/ic_launcher-web.png docs/ic_logo.png

# Build the site locally
mkdocs build
