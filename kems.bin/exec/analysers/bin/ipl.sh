if [ "$KEMS_HOME" = "" ] ; then
  echo "ERROR: KEMS_HOME not found in your environment."
  echo
  echo "Please, set the KEMS_HOME variable in your environment to match the"
  echo "location of the KEMS Prover version you want to use."
  exit 1
fi

cd $KEMS_HOME/kems.bin/exec/analysers/

./build.sh jflex -Danalyser.dir=ipl -Dlexer.name=ipl.flex

./build.sh cup -Danalyser.dir=ipl -Dparser.name=iplParser -Dsymbols.name=iplsym -Dcup.file.name=ipl.cup

./build.sh compile -Danalyser.dir=ipl -Danalyser.files=ipl

./build.sh makejar -Danalyser.dir=ipl -Djar.file=ipl.jar -Danalyser.files=ipl

cp $KEMS_HOME/kems.bin/exec/analysers/output.jars/ipl.jar $KEMS_HOME/kems.export/lib/generated/ipl.jar