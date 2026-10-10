# Al-Noor Zakat Calculator — Android Studio project

**Branding:** AL-NOOR ZAKAT AND CHARITY FOUNDATION • Light of Mercy

## Features
- Separate Android app project (not a website deployment).
- Bundled calculator interface works offline after installation.
- Optional online gold and silver spot-price updates, converted to NGN using public price feeds.
- Manual gold/silver price fields when offline or feeds are unavailable.
- Cash, metals, business stock, investments/receivables, eligible debts, 85g gold or 595g silver Nisab, Hawl selection, and 2.5% estimate.
- Uses the foundation logo supplied in the existing library.

## Build an APK
1. Open this folder in Android Studio (recent stable version).
2. Allow Gradle sync and install Android SDK Platform 35 if prompted.
3. Select **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
4. The debug APK is normally generated at `app/build/outputs/apk/debug/app-debug.apk`.

## Important notes
- This package is Android source code, not a prebuilt APK. It needs Android Studio/Android SDK to compile and sign.
- The online price feeds are public third-party services and may change, be unavailable, or return indicative values. Verify prices before relying on them.
- Zakat calculation depends on scholarly rulings and individual circumstances. The app provides an estimate, not a fatwa.
