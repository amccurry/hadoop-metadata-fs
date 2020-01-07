package hadoop.fs.mount;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class MountTest {

  @Test
  public void testMount() throws Exception {
    Path srcPath = new Path("real://test1/src");
    Path dstPath = new Path("virt://test2/dst");
    Mount mount = new Mount(srcPath, dstPath);
    Path realPath = mount.toMountPath(new Path(dstPath, "test1"));
    assertEquals(new Path(srcPath, "test1"), realPath);

    Path virutalPath = mount.fromMountPath(realPath);
    assertEquals(new Path(dstPath, "test1"), virutalPath);
  }

  @Test
  public void testMountRoot() throws Exception {
    Path srcPath = new Path("real://test1/src");
    Path dstPath = new Path("virt://test2/dst");
    Mount mount = new Mount(srcPath, dstPath);
    Path realPath = mount.toMountPath(dstPath);
    assertEquals(srcPath, realPath);

    Path virutalPath = mount.fromMountPath(realPath);
    assertEquals(dstPath, virutalPath);
  }
  
  @Test
  public void testMountSubDir() throws Exception {
    Path srcPath = new Path("real://test1/src");
    Path dstPath = new Path("virt://test2/dst");
    Mount mount = new Mount(srcPath, dstPath);
    Path realPath = mount.toMountPath(new Path(dstPath, "test1/test2/test3"));
    assertEquals(new Path(srcPath, "test1/test2/test3"), realPath);

    Path virutalPath = mount.fromMountPath(realPath);
    assertEquals(new Path(dstPath, "test1/test2/test3"), virutalPath);
  }
}