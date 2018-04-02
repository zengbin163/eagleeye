package com.dubboclub.dk.commons.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/** 
 * RSA 工具类。提供加密，解密，生成密钥对等方法。 
 * 需要到http://www.bouncycastle.org下载bcprov-jdk14-123.jar。 
 *  
 */
public class RSAUtils {

    public static final String MODULUS = "975d57b26ef4ede9f60f8ff4f92079d169ec1b6b9ec2195890b1921c8f20f2015dc951497df8b6b7b8958073b9aaf532fe76119948de08de312f45ae5572ebfead2e0d576ca4f0cfbda6f7739e1023a7a4a9ec74f2b3d30c164b138ca2c61dc64af260371590dcbc675ceaa933f86317066560c6b560c292c6670447a2737f97";
    public static final String PRIVATEEXPONENT = "7aa472032cae1dfb5c98fd6e6350f20340ccc2e6ef4a9bd8cbdc64e3e900abb579b53dbccb69f33a67401345312218ec898b46f5293a667b09e5209381becd015cfed99187b96c5d1abbbf1565b512d2aef71fedf6dd8357bbf50aa893391d547c3621dad775679bc74a6e582d048fac2e86e75512da3569449e261003b10a49";
    public static final String WEB_MODULUS = "845b0c959f370bcaae9dff448826ccec64616debac81b220d3302296c1f7cc845b83c449c0ae35bfe49869d0924466de5dfdf416998516e95bd4ec0fdfb825eb3c1b2864dad0786f35675f5cf63fbea271cb1079330ef84dab8941d140aa57f93c853e0518051e5751e78512667d8047b82e7e3adae0bf3dcb95c6a7852763db";
    public static final String WEB_PRIVATEEXPONENT = "528eb47fd3ab3348beb8c0d44f94dc380bb916266e190c321b6927fa1f8d60dda273c389398db0eb3c3cb1ac2ec049e6247cd6b70e158d200c6ef8bc42110c5d103cd5e5c51ce265f20dbd967231a505b5f179701e7b5e5e6d59530a9f291464c3101a18e7755fc9dfbefc091c52e51528d47a7b87a6f2349fb8c2a9ffc5f4d9";

    /** 
     * * 生成密钥对
     * @return KeyPair
     * @throws Exception
     */
    public static KeyPair generateKeyPair(File cfgFile) throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            final int KEY_SIZE = 1024;// 没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低  
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            saveKeyPair(keyPair, cfgFile);
            return keyPair;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * getKeyPair
     * @param cfgFile
     * @return
     * @throws Exception
     */
    public static KeyPair getKeyPair(File cfgFile) throws Exception {
        FileInputStream fis = new FileInputStream(cfgFile);
        ObjectInputStream oos = new ObjectInputStream(fis);
        KeyPair kp = (KeyPair) oos.readObject();
        oos.close();
        fis.close();
        return kp;
    }

    /**
     * saveKeyPair
     * @param kp
     * @param cfgFile
     * @throws Exception
     */
    public static void saveKeyPair(KeyPair kp, File cfgFile) throws Exception {
        FileOutputStream fos = new FileOutputStream(cfgFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //生成密钥  
        oos.writeObject(kp);
        oos.close();
        fos.close();
    }

    /** 
     * * 生成公钥
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey
     * @throws Exception 
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /** 
     * * 生成私钥 
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     * @throws Exception 
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /** 
     * * 加密 
     * @param pk 加密的密钥
     * @param data 待加密的明文数据
     * @return 加密后的数据 
     * @throws Exception 
     */
    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024  
            // 加密块大小为127  
            // byte,加密后为128个byte;因此共有2个加密块，第一个127  
            // byte第二个为1个byte  
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小  
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
                // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
                // ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了  
                // OutputSize所以只好用dofinal方法。  
                i++;
            }
            return raw;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /** 
     * 解密 
     * @param pk 解密的密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception 
     */
    @SuppressWarnings("static-access")
    public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(cipher.DECRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;

            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * hexStringToBytes
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (null == hexString || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * @param args
     * @throws Exception 
     */
    /*public static void main(String[] args) throws Exception {
    	String path = "D:\\RSAKey.txt";
        RSAPublicKey rsap = (RSAPublicKey) RSAUtils.generateKeyPair(new File(path)).getPublic(); 
    	KeyPair kp = generateKeyPair(new File(path));
    	saveKeyPair(kp, new File(path));
    	
      	File cfgFile = new File("D:\\RSAKey.txt");
    	PublicKey pk = getKeyPair(cfgFile).getPublic();
    	PrivateKey pik = getKeyPair(cfgFile).getPrivate();
    	
    	System.out.println(pk);
    	System.out.println(pik);
    	
        String test = "hello world";  
        byte[] en_test = encrypt(pk, test.getBytes());  
        byte[] de_test = decrypt(pik, en_test);  
        System.out.println(new String(de_test));
        
       // RSAPublicKey rsap = (RSAPublicKey) RSAUtils.getKeyPair(cfgFile).getPublic();  
        String module = rsap.getModulus().toString(16);  
        String empoent = rsap.getPublicExponent().toString(16);  
        System.out.println("module");  
        System.out.println(module);  
        System.out.println("empoent");  
        System.out.println(empoent);  
        
        String result = "2bda5137cb2a099ea77e5a46e7c398c00c9196c966909d73e57b402dca5589094ec5eff8232dd65db67ecd9202b94b512930d5d20ff18f11fe4b5673c2f952432279d163e4e90ba650c5d0720828bf73ec4628a6fbb20f5b80dd94641d38abe7fb6fb16d87863bde3e8ff56af43d168a3f9d8d8ac84a46c7c1bfaed86943d03f";  
        System.out.println("原文加密后为：");  
        System.out.println(result);  
        byte[] en_result = hexStringToBytes(result);//new BigInteger(result, 16).toByteArray();  
    //        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) RSAUtil.getKeyPair(cfgFile).getPrivate();
        String modulus = "975d57b26ef4ede9f60f8ff4f92079d169ec1b6b9ec2195890b1921c8f20f2015dc951497df8b6b7b8958073b9aaf532fe76119948de08de312f45ae5572ebfead2e0d576ca4f0cfbda6f7739e1023a7a4a9ec74f2b3d30c164b138ca2c61dc64af260371590dcbc675ceaa933f86317066560c6b560c292c6670447a2737f97";
        String privateExponent = "7aa472032cae1dfb5c98fd6e6350f20340ccc2e6ef4a9bd8cbdc64e3e900abb579b53dbccb69f33a67401345312218ec898b46f5293a667b09e5209381becd015cfed99187b96c5d1abbbf1565b512d2aef71fedf6dd8357bbf50aa893391d547c3621dad775679bc74a6e582d048fac2e86e75512da3569449e261003b10a49";
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) RSAUtils.generateRSAPrivateKey(new BigInteger(modulus, 16).toByteArray(), new BigInteger(privateExponent, 16).toByteArray());
        byte[] de_result = RSAUtils.decrypt(rsaPrivateKey, en_result);  
        System.out.println("还原密文：");  
        StringBuffer sb = new StringBuffer();  
        sb.append(new String(de_result));  
        System.out.println(sb.reverse().toString().substring(13));  
    
    
    }  */
}
