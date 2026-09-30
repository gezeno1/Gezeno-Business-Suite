# Gezeno Business Suite — Heavy Android Build

Offline-first native Android B2B business application. No third-party runtime libraries are required; data is stored in SQLite on the device.

## Included modules
Dashboard, Customers, Suppliers/Vendors, Products, Purchases, Orders, Invoices, Payments, Stock, Reports, Customer Catalogue PDF, Backup/Restore, Settings/Branding/Login.

## Demo login
Username: `admin`
Password: `gezeno123`

## Build
Open in Android Studio or run GitHub Actions workflow `Build Gezeno Business Suite APK`. The workflow installs Gradle 8.9 and builds `app-debug.apk`.

## Notes
This is a source/build package, not a precompiled APK. Test the business rules and PDF output on a real Android device before production use.
