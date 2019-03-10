package RSA.generateKeys;

import RSA.Parameters;
import RSA.math.HashAlgorithm;
import RSA.math.HashAlgorithmMD5;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

public class SavePrivateAndPublicKey {

    public void savePrivateKey(GenerateKeysRSA generateKeysRSA){
        HashAlgorithm hash = new HashAlgorithmMD5();
        ArrayList<String> mass = new ArrayList<>();
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPrivateKey()[0].getBytes(Parameters.Encode)));
        mass.add(Base64.getEncoder().encodeToString(LocalDate.now().plusDays
                (Parameters.PrivateKeyValidTime).toString().getBytes(Parameters.Encode)));
        mass.add(hash.getHashString(mass.get(0) + mass.get(1)));
        new SaveFileWin().saveFile(Paths.get(Parameters.SavePrivateKey1Path), mass, Parameters.Encode);
        mass.clear();
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPrivateKey()[1].getBytes(Parameters.Encode)));
        mass.add(hash.getHashString(mass.get(0)));
        new SaveFileWin().saveFile(Paths.get(Parameters.SavePrivateKey2Path), mass, Parameters.Encode);
    }

    public void savePublicKey(GenerateKeysRSA generateKeysRSA){
        HashAlgorithm hash = new HashAlgorithmMD5();
        ArrayList<String> mass = new ArrayList<>();
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPublicKey()[0].getBytes(Parameters.Encode)));
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPublicKey()[1].getBytes(Parameters.Encode)));
        mass.add(Base64.getEncoder().encodeToString(LocalDate.now().plusDays
                (Parameters.PrivateKeyValidTime).toString().getBytes(Parameters.Encode)));
        mass.add(hash.getHashString(mass.get(0) + mass.get(1) + mass.get(2)));
        new SaveFileWin().saveFile(Paths.get(Parameters.SavePublicKey1Path), mass, Parameters.Encode);
    }
}
