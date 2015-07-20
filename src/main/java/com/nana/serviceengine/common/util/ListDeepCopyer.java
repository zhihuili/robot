package com.nana.serviceengine.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ListDeepCopyer {
	public static List copy(List src) throws IOException,ClassNotFoundException {
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteout);
		out.writeObject(src);
		ByteArrayInputStream bytein = new ByteArrayInputStream(
				byteout.toByteArray());
		ObjectInputStream in = new ObjectInputStream(bytein);
		List dest = (List) in.readObject();
		return dest;

	}
}
