# Build the Al-Noor Zakat Calculator APK using only an Android phone

This source project is not itself an installable APK. The included GitHub Actions workflow can compile it in the cloud, so a computer is not required. You will need a GitHub account and an internet connection.

1. Download and extract `AlNoor_Zakat_Calculator_Android_Project.zip` on your phone, or upload the ZIP contents to a new GitHub repository using GitHub's website. The repository's top level must contain `settings.gradle`, `build.gradle`, `gradle.properties`, `app/`, and `.github/`.
2. In GitHub, open the repository's **Actions** tab. If prompted, enable Actions for the repository.
3. Choose **Build Al-Noor Zakat Calculator APK** from the workflow list, tap **Run workflow**, then run it on the `main` branch.
4. Wait for the workflow to finish successfully. Open the completed run and find **Artifacts** at the bottom.
5. Download the `Al-Noor-Zakat-Calculator-APK` artifact. GitHub downloads it as a ZIP. Extract it to find `app-debug.apk`.
6. Tap `app-debug.apk` to install. Android may ask you to allow installation from your browser or file manager; only enable this for the app you have just built and turn it off afterward if desired.

If the GitHub mobile website makes uploading the project difficult, open it in your browser's **Desktop site** mode. Do not share your GitHub password or verification codes with anyone.

This is a debug APK for testing, not a Play Store release. Test calculations and price updates before distributing it widely.
