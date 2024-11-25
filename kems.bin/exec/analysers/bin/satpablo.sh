if [ "$KEMS_HOME" = "" ] ; then
  echo "ERROR: KEMS_HOME not found in your environment."
  echo
  echo "Please, set the KEMS_HOME variable in your environment to match the"
  echo "location of the KEMS Prover version you want to use."
  exit 1
fi

cd $KEMS_HOME/kems.bin/exec/analysers/

./build.sh jflex -Danalyser.dir=satpablo -Dlexer.name=satlib-sat-s5.flex

./build.sh cup -Danalyser.dir=satpablo -Dparser.name=satpabloParser -Dsymbols.name=satpablosym -Dcup.file.name=satlib-sat-s5.cup

./build.sh compile -Danalyser.dir=satpablo -Danalyser.files=satpablo

./build.sh makejar -Danalyser.dir=satpablo -Djar.file=satpablo.jar -Danalyser.files=satpablo

cp $KEMS_HOME/kems.bin/exec/analysers/output.jars/satpablo.jar $KEMS_HOME/kems.export/lib/generated/satpablo.jar
