/**  
 * Project Name		batch  
 * File Name		TrackIPMaskUtil.java  
 * Package Name		cn.secure.util  
 * Date				2018年5月25日下午3:14:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description	ip操作工具
 * @ClassName	TrackIPMaskUtil
 * @author		ygc
 * @date		2018年5月25日 下午3:14:26
 * @version		V1.0
 * @since		JDK 1.8
 */
public class TrackIPMaskUtil
{
  public static void main(String args[]) throws UnknownHostException
  {
    TrackIPMaskUtil ipmask;
    
    ipmask = TrackIPMaskUtil.getIPMask("210.72.10.0/24");
    System.out.println("Checking "+ipmask+"...");

    test(ipmask, "210.72.10.255", true);
    test(ipmask, "210.72.11.1", false);
    System.out.println(isBelong("210.72.10.255",TrackIPMaskUtil.getIPMask("210.72.10.0/24")));
    System.out.println(isBelong("210.72.11.1",TrackIPMaskUtil.getIPMask("210.72.10.0/24")));
    
  }
  /**
   * test	(测试ip地址格式)<br/>
   * @param ipmask
   * @param addr
   * @param expect
   * @throws UnknownHostException
   * @author	ygc  
   * @since	JDK 1.8
   */
  public static void test(TrackIPMaskUtil ipmask, String addr, boolean expect) 
      throws UnknownHostException
  {
    boolean got = ipmask.matches(addr);
    System.out.println(addr + "\t(" + expect + ") ?\t"+got
        + "\t" + (got==expect?"":"!!!!!!!!"));
  }
  
  /*
   * check whether addr is belong the range of ipmask
   */
  /**
   * isBelong	(判断ip和地址是否属于同样 )<br/>
   * @param addr 添加地址
   * @param ipmask 
   * @return
   * @throws UnknownHostException
   * @author	ygc  
   * @since	JDK 1.8
   */
  public static boolean isBelong(String addr, TrackIPMaskUtil ipmask) throws UnknownHostException {
    boolean got = ipmask.matches(addr);
    return got;
  }
  
  

  private Inet4Address i4addr;
  private byte maskCtr;

  private int addrInt;
  private int maskInt;
  /**
   * ip地址转换工具
   * Creates a new instance of TrackIPMaskUtil.  
   *  
   * @param i4addr
   * @param mask
   */
  public TrackIPMaskUtil(Inet4Address i4addr, byte mask)
  {
    this.i4addr = i4addr;
    this.maskCtr = mask;

    this.addrInt = addrToInt(i4addr);
    this.maskInt = ~((1 << (32 - maskCtr)) - 1);
  }

  /** IPMask factory method. 
   * 
   * @param addrSlashMask IP/Mask String in format "nnn.nnn.nnn.nnn/mask". If 
   *    the "/mask" is omitted, "/32" (just the single address) is assumed.
   * @return a new IPMask
   * @throws UnknownHostException if address part cannot be parsed by 
   *    InetAddress
   */
  /**
   * getIPMask	(获取ip地址)<br/>
   * @param addrSlashMask
   * @return
   * @throws UnknownHostException
   * @author	ygc  
   * @since	JDK 1.8
   */
  public static TrackIPMaskUtil getIPMask(String addrSlashMask) 
      throws UnknownHostException
  {
    int pos = addrSlashMask.indexOf('/');
    String addr;
    byte maskCtr;
    if (pos==-1)
    {
      addr = addrSlashMask;
      maskCtr = 32;
    }
    else
    { 
      addr = addrSlashMask.substring(0, pos);
      maskCtr = Byte.parseByte(addrSlashMask.substring(pos + 1));
    }
    return new TrackIPMaskUtil((Inet4Address) InetAddress.getByName(addr), maskCtr);
  }

 /** Test given IPv4 address against this IPMask object.
   * 
   * @param testAddr address to check.
   * @return true if address is in the IP Mask range, false if not.
   */  
  /**
   * matches	(匹配测试地址)<br/>
   * @param testAddr
   * @return
   * @author	ygc  
   * @since	JDK 1.8
   */
  public boolean matches(Inet4Address testAddr)
  {
    int testAddrInt = addrToInt(testAddr);   
    return ((addrInt & maskInt) == (testAddrInt & maskInt));
  }

/** Convenience method that converts String host to IPv4 address.
   * 
   * @param addr IP address to match in nnn.nnn.nnn.nnn format or hostname.
   * @return true if address is in the IP Mask range, false if not.
   * @throws UnknownHostException if the string cannot be decoded.
   */
  /**
   * matches	(匹配地址)<br/>
   * @param addr
   * @return
   * @throws UnknownHostException
   * @author	ygc  
   * @since	JDK 1.8
   */
  public boolean matches(String addr) 
      throws UnknownHostException
  {
    return matches((Inet4Address)InetAddress.getByName(addr));
  }

/** Converts IPv4 address to integer representation.
   */
  /**
   * addrToInt	(这里用一句话描述这个方法的作用)<br/>
   * @param i4addr
   * @return
   * @author	ygc  
   * @since	JDK 1.8
   */
  private static int addrToInt(Inet4Address i4addr)
  {
    byte[] ba = i4addr.getAddress();  
    return (ba[0]       << 24) 
        | ((ba[1]&0xFF) << 16) 
        | ((ba[2]&0xFF) << 8) 
        |  (ba[3]&0xFF);
  }
  /**
   * 
   * 获取主机地址
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "IPMask(" + i4addr.getHostAddress() + "/" + maskCtr + ")";
  }
/**
 * 比较地址
 * @see java.lang.Object#equals(java.lang.Object)
 */
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final TrackIPMaskUtil that = (TrackIPMaskUtil) obj;    
    return (this.addrInt == that.addrInt && this.maskInt == that.maskInt);
  }

  @Override
  public int hashCode()
  {
    return this.maskInt + this.addrInt;
  }

}