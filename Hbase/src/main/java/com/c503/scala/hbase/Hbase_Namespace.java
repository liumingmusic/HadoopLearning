package com.c503.scala.hbase;

import com.c503.base.Hbase_Base;
import com.c503.exception.CustomException;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

/**
 * 描述: 操作与namespace相关的API
 *
 * @Author liumm
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Date 2018-06-25 19:35
 */
public class Hbase_Namespace extends Hbase_Base {

    public static void main(String[] args) throws IOException, CustomException {

        Hbase_Namespace hbase_namespace = new Hbase_Namespace();

        Connection connection = hbase_namespace.connectToHbase(hbaseMasterIPS, hbaseMasterPort, hbaseZnode);

        //列出命名空间
        hbase_namespace.listNameSpace(connection.getAdmin());

        //创建命名空间
        //hbase_namespace.createNameSpace(connection.getAdmin());

        //查看命名空间
        //hbase_namespace.descNameSpace(connection.getAdmin());

        //修改命名空间
        //hbase_namespace.modifyNameSpace(connection.getAdmin());

        //删除命名空间
        //hbase_namespace.dropNameSpace(connection.getAdmin());
    }

    /**
     * @param admin
     * @return void
     * @method listNameSpace
     * @description 查看命名空间
     * @date: 18/6/25 20:10
     * @author: liumm
     */
    private void listNameSpace(Admin admin) throws IOException {
        NamespaceDescriptor[] descriptors = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor desc : descriptors) {
            System.out.println("命名空间：" + desc.getName());
        }
    }

    /**
     * @param admin
     * @return void
     * @method createNameSpace
     * @description 创建命名空间
     * @date: 18/6/25 20:30
     * @author: liumm
     */
    private void createNameSpace(Admin admin) throws IOException {
        NamespaceDescriptor namespace = NamespaceDescriptor.create("liumm_namespace").build();
        admin.createNamespace(namespace);
    }

    /**
     * @param admin
     * @return void
     * @method descNameSpace
     * @description 查询指定的命名空间
     * @date: 18/6/25 20:34
     * @author: liumm
     */
    private void descNameSpace(Admin admin) throws IOException {
        NamespaceDescriptor liumm_namespace = admin.getNamespaceDescriptor("liumm_namespace");
        System.out.println("命名空间：" + liumm_namespace);
    }

    /**
     * @param admin
     * @return void
     * @method modifyNameSpace
     * @description 修改命名空间
     * @date: 18/6/25 20:37
     * @author: liumm
     */
    private void modifyNameSpace(Admin admin) throws IOException {
        NamespaceDescriptor liumm_namespace = NamespaceDescriptor.create("liumm_namespace").build();
        liumm_namespace.setConfiguration("key_1", "value_1");
        liumm_namespace.setConfiguration("key_2", "value_2");
        liumm_namespace.setConfiguration("key_3", "value_3");
        admin.modifyNamespace(liumm_namespace);
    }

    /**
     * @param admin
     * @return void
     * @method dropNameSpace
     * @description 删除命名空间
     * @date: 18/6/25 20:41
     * @author: liumm
     */
    private void dropNameSpace(Admin admin) throws IOException {
        admin.deleteNamespace("liumm_namespace");
    }

}
