package RSA.generateKeys;

import RSA.Parameters;
import RSA.math.HashAlgorithm;
import RSA.math.HashAlgorithmMD5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

public class SavePrivateAndPublicKey {
    private SaveFile saveFile = null;

    public SavePrivateAndPublicKey() {
        try {
            saveFile = (SaveFile) Class.forName("SaveFile").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert saveFile != null;
    }

    public void savePrivateKey(GenerateKeysRSA generateKeysRSA) {
        HashAlgorithm hash = new HashAlgorithmMD5();
        ArrayList<String> mass = new ArrayList<>();
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPrivateKey()[0].getBytes(Parameters.Encode)));
        mass.add(Base64.getEncoder().encodeToString(LocalDate.now().plusDays
                (Parameters.PrivateKeyValidTime).toString().getBytes(Parameters.Encode)));
        mass.add(hash.getHashString(mass.get(0) + mass.get(1)));
        saveFile.saveFile(Parameters.SavePrivateKey1Path, mass, Parameters.Encode);
        mass.clear();
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPrivateKey()[1].getBytes(Parameters.Encode)));
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPrivateKey()[2].getBytes(Parameters.Encode)));
        mass.add(hash.getHashString(mass.get(0) + mass.get(1)));
        saveFile.saveFile(Parameters.SavePrivateKey2Path, mass, Parameters.Encode);
    }

    public void savePublicKey(GenerateKeysRSA generateKeysRSA) {
        HashAlgorithm hash = new HashAlgorithmMD5();
        ArrayList<String> mass = new ArrayList<>();
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPublicKey()[0].getBytes(Parameters.Encode)));
        mass.add(Base64.getEncoder().encodeToString(generateKeysRSA.getPublicKey()[1].getBytes(Parameters.Encode)));
        mass.add(Base64.getEncoder().encodeToString(LocalDate.now().plusDays
                (Parameters.PrivateKeyValidTime).toString().getBytes(Parameters.Encode)));
        mass.add(hash.getHashString(mass.get(0) + mass.get(1) + mass.get(2)));
        saveFile.saveFile(Parameters.SavePublicKey1Path, mass, Parameters.Encode);
    }
}
