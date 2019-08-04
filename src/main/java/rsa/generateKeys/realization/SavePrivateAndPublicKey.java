package rsa.generateKeys.realization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.config.Parameters;
import rsa.generateKeys.interfaces.SaveFile;
import rsa.math.interfaces.HashAlgorithm;
import rsa.math.realization.HashAlgorithmMD5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

@Component
public class SavePrivateAndPublicKey {

    @Autowired
    private SaveFile saveFile;

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
