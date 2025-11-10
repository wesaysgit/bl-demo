//package com.es.base64;
//
//import jodd.util.StringUtil;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.security.KeyStore;
//import java.security.MessageDigest;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLException;
//import javax.net.ssl.SSLSocket;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//import javax.net.ssl.X509TrustManager;
//
//
///**
// * 自动导入证书.
// * @author huangzongcheng
// * 2017.06.13
// */
//public class SSLCertUtil {
//
//	private static final String TAG = SSLCertUtil.class.getSimpleName();
//
//	public static void main(String[] args) {
//		try {
//			importCert("www.kuaidi100.com",null);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 淇濆瓨瀵煎叆SSL璇佷功
//	 * @param url
//	 * @param pwd
//	 * @throws Exception
//	 */
//    public static void importCert(String url,String pwd) throws Exception {
//
//		System.out.println(TAG+"http url format = xxx.com[:port] ");
//		System.out.println(TAG+String.format("getting %s info",url));
//
//    	//瑙ｆ瀽鍦板潃锛屽垎绂荤鍙�
//	    String[] urlformat = url.split(":");
//        String host = urlformat[0];
//        int port = (urlformat.length == 1) ? 443 : Integer.parseInt(urlformat[1]);
//        String p = StringUtil.isEmpty(pwd) ? "changeit" : pwd;
//        char[] passphrase = p.toCharArray();
//        String certName = host.replaceAll("[.]", "");
//
//
//	    File file = new File(certName);
//	    if (!file.isFile()) {
//	        char SEP = File.separatorChar;
//	        File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
//	        file = new File(dir, certName);
//	        if (!file.isFile()) {
//	        	file = new File(dir, "cacerts");
//	        }
//	    }
//
//		System.out.println(TAG+String.format("loading keystore %s", file));
//
//	    InputStream in = new FileInputStream(file);
//	    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
//	    ks.load(in, passphrase);
//	    in.close();
//
//	    SSLContext context = SSLContext.getInstance("TLS");
//	    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//	    tmf.init(ks);
//	    X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
//	    SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
//	    context.init(null, new TrustManager[] {tm}, null);
//	    SSLSocketFactory factory = context.getSocketFactory();
//
//		System.out.println(TAG+String.format("opening connection to %s:%d", host,port));
//
//	    SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
//	    socket.setSoTimeout(10000);
//	    try {
//	        System.out.println(TAG+"Starting SSL handshake");
//	        socket.startHandshake();
//	        socket.close();
//	        System.out.println(TAG+"No errors, certificate is already trusted");
//	    } catch (SSLException e) {
//	        System.out.println();
//	        e.printStackTrace(System.out);
//	    }
//
//	    X509Certificate[] chain = tm.chain;
//	    if (chain == null) {
//	        System.out.println(TAG+"Could not obtain server certificate chain");
//	        return;
//	    }
//
//	    System.out.println(TAG+"Server sent " + chain.length + " certificate(s):");
//
//	    MessageDigest sha1 = MessageDigest.getInstance("SHA1");
//	    MessageDigest md5 = MessageDigest.getInstance("MD5");
//	    for (int i = 0; i < chain.length; i++) {
//	        X509Certificate cert = chain[i];
//	        System.out.println (" " + (i + 1) + " Subject " + cert.getSubjectDN());
//	        System.out.println("   Issuer  " + cert.getIssuerDN());
//	        sha1.update(cert.getEncoded());
//	        System.out.println("   sha1    " + toHexString(sha1.digest()));
//	        md5.update(cert.getEncoded());
//	        System.out.println("   md5     " + toHexString(md5.digest()));
//	        System.out.println();
//	    }
//
//	    int k = 0;
//	    X509Certificate cert = chain[k];
//	    String alias = host.replaceAll("[.]", "");
//	    ks.setCertificateEntry(alias, cert);
//	    OutputStream out = new FileOutputStream(FileUtil.getCertPath()+certName);
//	    ks.store(out, passphrase);
//	    out.close();
//	    System.out.println(cert);
//	    System.out.println("Added certificate to keystore "+certName+" using alias '"+ alias + "'");
//    }
//
//    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();
//
//    private static String toHexString(byte[] bytes) {
//	    StringBuilder sb = new StringBuilder(bytes.length * 3);
//	    for (int b : bytes) {
//	        b &= 0xff;
//	        sb.append(HEXDIGITS[b >> 4]);
//	        sb.append(HEXDIGITS[b & 15]);
//	        sb.append(' ');
//	    }
//	    return sb.toString();
//    }
//
//    private static class SavingTrustManager implements X509TrustManager {
//
//	    private final X509TrustManager tm;
//	    private X509Certificate[] chain;
//
//	    SavingTrustManager(X509TrustManager tm) {
//	        this.tm = tm;
//	    }
//
//	    public X509Certificate[] getAcceptedIssuers() {
//	        throw new UnsupportedOperationException();
//	    }
//
//	    public void checkClientTrusted(X509Certificate[] chain, String authType)
//	        throws CertificateException {
//	        throw new UnsupportedOperationException();
//	    }
//
//	    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//	        this.chain = chain;
//	        tm.checkServerTrusted(chain, authType);
//	    }
//    }
//
//}
