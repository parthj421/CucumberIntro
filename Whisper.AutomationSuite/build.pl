$MAKELIB_PL     = "$ENV{ GBE_TOOLS }/makelib.pl";
$BUILDLIB_PL    = "$ENV{ GBE_TOOLS }/buildlib.pl";
require         "$BUILDLIB_PL";
require         "$MAKELIB_PL";

BuildPlatforms ( 'JAVA,--Version=1.8','--OnlyProd' );

BuildName      ( 'whisper_automation_hiptest 1.00.0000 cr' );

BuildInterface ( 'local' );
BuildInterface ( 'interface' );
#LinkPkgArchive ( 'whip_framework', '3.1.2005.cr' );
#LinkPkgArchive ( 'whip_pulse', '2.1.955000.cr' );
#LinkPkgArchive ( 'whip_pulse_hiptest_support', '1.0.14284.cr' );
BuildDescpkg   ();
BuildVersion   ( '--Style=properties' );
BuildMake      ();
