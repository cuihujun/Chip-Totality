@Echo Off
Echo Kopiowanie assetow
XCOPY ".\BuildingsPack" "..\ChipTotality-android\assets\BuildingsPack" /S /I /Y
XCOPY ".\ExplosionsPack" "..\ChipTotality-android\assets\ExplosionsPack" /S /I /Y
XCOPY ".\IconsPack" "..\ChipTotality-android\assets\IconsPack" /S /I /Y

pause