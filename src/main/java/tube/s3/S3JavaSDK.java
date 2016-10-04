package tube.s3;

import java.io.File;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

public class S3JavaSDK {
	private static final String BUCKET_NAME = "tube.videobucket";

	public static String uploadFileToS3AWS(String name, MultipartFile file) throws Exception {

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.ACCESS_KEY_ID,
				Credentials.SECRET_ACCESS_KEY);

		AmazonS3Client s3Client = new AmazonS3Client(awsCreds);
		
		InputStream is = file.getInputStream();
		s3Client.putObject(new PutObjectRequest(BUCKET_NAME, name, is, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
		S3Object s3Object = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, name));
		
		System.out.println(s3Object.getObjectContent().getHttpRequest().getURI().toString());
		System.out.println(s3Object.getObjectContent().getHttpRequest().getURI().toString().length());
		/*
		 * remember this is a new bucket and "folders" dont exist in S3, they
		 * are logical entities derived from the path specified in the key. S3
		 * is just a key value store. they are created on the fly when we upload
		 * an object with a specific key path Also, the folder setting in the
		 * console on the S3 folder for server side encryption is a slightly
		 * misleading instruction to encrypt the selected resources - it does
		 * NOT set a persistant setting on all resources uploaded into that
		 * folder
		 */
//			PutObjectRequest putRequest1 = new PutObjectRequest(BUCKET_NAME, fileName, file);
//			PutObjectResult response1 = s3Client.putObject(putRequest1);
//			System.out.println("Uploaded object encryption status is " + response1.getSSEAlgorithm());

		return s3Object.getObjectContent().getHttpRequest().getURI().toString();
	}

	public static void serverSideEncryption() throws Exception {

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.ACCESS_KEY_ID,
				Credentials.SECRET_ACCESS_KEY);

		AmazonS3Client s3Client = new AmazonS3Client(awsCreds);

		for (Bucket bucket : s3Client.listBuckets()) {

			BucketUtils.deleteBucket(bucket.getName(), s3Client);

		}

		String newBucketName = "kaloyan" + System.currentTimeMillis();
		s3Client.createBucket(newBucketName);

		String policy = BucketUtils.readFileFromResources("encrypted-folder-policy.txt").replace("bucketname",
				newBucketName);

		/*
		 * This is a bucket policy - the bucket itself must be mentioned in the
		 * policy explicitly
		 */

		System.out.println(policy);
		s3Client.setBucketPolicy(newBucketName, policy);

		final String fileName = "sometext.txt";

		File file = new File(S3JavaSDK.class.getResource(fileName).toURI());

		/*
		 * remember this is a new bucket and "folders" dont exist in S3, they
		 * are logical entities derived from the path specified in the key. S3
		 * is just a key value store. they are created on the fly when we upload
		 * an object with a specific key path Also, the folder setting in the
		 * console on the S3 folder for server side encryption is a slightly
		 * misleading instruction to encrypt the selected resources - it does
		 * NOT set a persistant setting on all resources uploaded into that
		 * folder
		 */

		{
			PutObjectRequest putRequest1 = new PutObjectRequest(newBucketName,
					"unencrypted/" + fileName + "." + System.currentTimeMillis(), file);
			PutObjectResult response1 = s3Client.putObject(putRequest1);
			System.out.println("Uploaded object encryption status is " + response1.getSSEAlgorithm());
		}
		{
			PutObjectRequest putRequest1 = new PutObjectRequest(newBucketName,
					"encrypted/" + fileName + "." + System.currentTimeMillis(), file);

			try {
				PutObjectResult response1 = s3Client.putObject(putRequest1);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("was not able to store an unencrypted file in this folder");
			}

		}
		{
			PutObjectRequest putRequest1 = new PutObjectRequest(newBucketName,
					"encrypted/" + fileName + "." + System.currentTimeMillis(), file);
			ObjectMetadata objectMetadata1 = new ObjectMetadata();
			objectMetadata1.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
			putRequest1.setMetadata(objectMetadata1);

			PutObjectResult response1 = s3Client.putObject(putRequest1);
			System.out.println("Uploaded object encryption status is " + response1.getSSEAlgorithm());
		}

	}

	public static void serverSideEncryptionNotResource() throws Exception {

		BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.ACCESS_KEY_ID,
				Credentials.SECRET_ACCESS_KEY);

		AmazonS3Client s3Client = new AmazonS3Client(awsCreds);

		BucketUtils.deleteAllBuckets(s3Client);

		String newBucketName = "kaloyan" + System.currentTimeMillis();
		s3Client.createBucket(newBucketName);

		String policy = BucketUtils.readFileFromResources("encrypted-folder-policy-notresource.txt")
				.replace("bucketname", newBucketName);

		/*
		 * This is a bucket policy - the bucket itself must be mentioned in the
		 * policy explicitly
		 */

		System.out.println(policy);
		s3Client.setBucketPolicy(newBucketName, policy);

		final String fileName = "sometext.txt";

		File file = new File(S3JavaSDK.class.getResource(fileName).toURI());

		/*
		 * remember this is a new bucket and "folders" dont exist in S3, they
		 * are logical entities derived from the path specified in the key. S3
		 * is just a key value store. they are created on the fly when we upload
		 * an object with a specific key path Also, the folder setting in the
		 * console on the S3 folder for server side encryption is a slightly
		 * misleading instruction to encrypt the selected resources - it does
		 * NOT set a persistant setting on all resources uploaded into that
		 * folder
		 */

		{
			PutObjectRequest putRequest1 = new PutObjectRequest(newBucketName,
					"unencrypted/" + fileName + "." + System.currentTimeMillis(), file);
			PutObjectResult response1 = s3Client.putObject(putRequest1);
			System.out.println("Uploaded object encryption status is " + response1.getSSEAlgorithm());
		}
		{
			PutObjectRequest putRequest1 = new PutObjectRequest(newBucketName,
					"bananas/" + fileName + "." + System.currentTimeMillis(), file);

			try {
				PutObjectResult response1 = s3Client.putObject(putRequest1);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("was not able to store an unencrypted file in this folder");
			}

		}
		{
			PutObjectRequest putRequest1 = new PutObjectRequest(newBucketName,
					"bananas/" + fileName + "." + System.currentTimeMillis(), file);
			ObjectMetadata objectMetadata1 = new ObjectMetadata();
			objectMetadata1.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
			putRequest1.setMetadata(objectMetadata1);

			PutObjectResult response1 = s3Client.putObject(putRequest1);
			System.out.println("Uploaded object encryption status is " + response1.getSSEAlgorithm());
		}
	}
}
