package JavaBasicAlgorithm.L_Huffman;


import java.io.*;
import java.util.*;

/**
 * Create By 奇怪君 ON 2020/4/22.
 */
public class HuffmanCode {
    public static void main(String[] args) {
        /*
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanCodesBytes));
        System.out.println(huffmanCodesBytes.length);
        */
        /*
        List<HNode> nodes = getNodes(contentBytes);
        System.out.println(nodes);
        //创建二叉树
        HNode huffmanTreeRoot = createHuffmanTree(nodes);
        huffmanTreeRoot.preOrder();

        //测试是否生成了对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println(huffmanCodes);

        byte[] bytes = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(bytes));
        */

//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println(new String(sourceBytes));
        //测试压缩文件
//        String srcFile="E://lena.jpg";
//        String dstFile="E://dst.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件OK~~~");
        //测试解压文件
        String srcFile="E://dst.zip";
        String dstFile="E://src.jpg";
        unZipFile(srcFile,dstFile);
        System.out.println("解压成功");
    }

    //使用一个方法，将前面的方法封装起来，便于我们的调用

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<HNode> nodes = getNodes(bytes);
        HNode huffmanTreeRoot = createHuffmanTree(nodes);

        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;

    }


    //编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]

    /**
     *
     * @param bytes 这时原始的字符串对应的byte[]
     * @param huffmanCodes  生成的赫夫曼编码map
     * @return  返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //1. 利用huffmanCodes将bytes转成 哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //2. 遍历bytes数组
        for (byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println(stringBuilder.toString());
        //将"10101..."转成byte[]
        //统计返回byte[] huffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 == 0){
            len=stringBuilder.length()/8;
        }else {
            len =stringBuilder.length() /8 +1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index=0;//记录第几个byte
        for (int i = 0; i < stringBuilder.length() ; i+=8) {
            String strByte;
            if (i+8 > stringBuilder.length()){
                strByte=stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i,i+8);
            }
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;

    }






    //生成哈夫曼树对应的哈夫曼编码
    //思路：
    //1. 将哈夫曼编码表存放在Map<Byte,String>形式
    static HashMap<Byte,String> huffmanCodes = new HashMap<>();
    //2. 在生成哈夫曼编码表时，需要取拼接路径，定义一个StrinBuilder存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载getCodes
    private static Map<Byte,String> getCodes(HNode root){
        if (root==null){
            return null;
        }
        //处理root的左子树
        getCodes(root.left,"0",stringBuilder);
        //处理root的右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }
    /**
     * 功能：将传入的node结点的所有叶子结点的哈夫曼编码得到，并放入到huffmanCodes集合
     * @param node   出入的节点
     * @param code   路径：左子节点是0，右子节点是1
     * @param stringBuilder  用于拼接路径
     */
    private static void getCodes(HNode node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null){
            //判断当前结点是叶子结点还是非叶子节点
            if (node.data == null){//非叶子节点
                //左递归
                getCodes(node.left,"0",stringBuilder2);
                //右递归
                getCodes(node.right,"1",stringBuilder2);
            }else {//说明是一个叶子结点
                //就表示找到某个叶子结点
                huffmanCodes.put(node.data,stringBuilder2.toString());

            }
        }

    }



    //前序遍历的方法
    private static void preOrder(HNode root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("哈夫曼树是空树");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回的就是List形式，[Node[data=97,weight=5]...]
     */
    private static List<HNode> getNodes(byte[] bytes) {
        ArrayList<HNode> nodes = new ArrayList<>();

        //使用map方法统计每个字符出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();

        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count+1);//注意：不是count++
            }
        }

        // 把每个键值对转成一个HNode对象，并加入到nodes集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过List创建对应的哈夫曼树
    private static HNode createHuffmanTree(List<HNode> nodes) {
        while (nodes.size() > 1) {
            //1. 进行排序
            Collections.sort(nodes);
            //2. 取出前两个
            HNode leftnode = nodes.get(0);
            HNode rightnode = nodes.get(1);
            //3.将这两个添加到List中
            HNode parent = new HNode(null, leftnode.weight + rightnode.weight);
            parent.left = leftnode;
            parent.right = rightnode;
            //4.删除前两个
            nodes.remove(leftnode);
            nodes.remove(rightnode);
            //5. 将新的节点添加到nodes中
            nodes.add(parent);
        }
        // nodes最后的节点，就是哈夫曼树的跟节点
        return nodes.get(0);
    }




    //进行解码
    //1. 将huffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重写先转成哈夫曼编码对应的二进制的字符串"1010100..."
    //2. 哈夫曼编码对应的二进制的字符串  => 对照哈夫曼编码  =>  "i like like like java ..."

    private static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        int temp = b;
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length()-8);
        }else{
            return str;
        }

    }

    //编写一个方法，完成对压缩数据的解码

    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        // 1. 先得到huffmanBytes 对应的二进制的字符串，形式1010100...
        StringBuilder stringBuilder =new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag=(i == huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag,b));
        }

        //把字符串按照指定的哈夫曼编码进行解码
        //把赫夫曼编码进行调换，因为反向查询a->100 100->1
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //创建临时集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag=true;
            Byte b = null;
            while (flag){
                //递增的取出key 1
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b==null){
                    count++;
                }else {
                    flag=false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count
        }
        //当for循环结束后，我们list就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length ; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //编写方法，将一个文件进行压缩

    /**
     *
     * @param srcFile  你传入的希望压缩的文件的全路径
     * @param dstFile  我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos=null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            is.read(b);
            //获取到文件对应的哈夫曼编码表
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);//
            //这里我们以对象流的方式写入哈夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                os.close();
                oos.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    //编写一个方法，完成对压缩文件的解压

    /**
     *
     * @param zipFile  准备解压的文件
     * @param dstFile  将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile,String dstFile){
        //定义文件的输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os=null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes,huffmanBytes);
            //将bytes数组写入到目录中
            os = new FileOutputStream(dstFile);
            //写数据到dstFile
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}


class HNode implements Comparable<HNode> {
    Byte data;//存放数据本身，比如'a'=>97   ' '=>32
    int weight;//权值，表示字符出现的次数
    HNode left;
    HNode right;

    public HNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }
}
