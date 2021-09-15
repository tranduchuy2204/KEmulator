package javax.microedition.m3g;


import java.lang.ref.WeakReference;
import java.util.Enumeration;
import java.util.Hashtable;

class Engine {
   static final int ANIMATIONCONTROLLER = 0;
   static final int ANIMATIONTRACK = 1;
   static final int APPEARANCE = 2;
   static final int BACKGROUND = 3;
   static final int CAMERA = 4;
   static final int COMPOSITINGMODE = 5;
   static final int COMPRESSEDIMAGE2D = 6;
   static final int FOG = 7;
   static final int GRAPHICS3D = 8;
   static final int GROUP = 9;
   static final int IMAGE2D = 10;
   static final int KEYFRAMESEQUENCE = 11;
   static final int LIGHT = 12;
   static final int LOADER = 13;
   static final int MATERIAL = 14;
   static final int MESH = 15;
   static final int MORPHINGMESH = 16;
   static final int PLASMAIMAGE = 17;
   static final int POLYGONMODE = 18;
   static final int RAYINTERSECTION = 19;
   static final int SKINNEDMESH = 20;
   static final int SPRITE3D = 21;
   static final int STAGESET = 22;
   static final int TEXTURE2D = 23;
   static final int TRANSFORM = 24;
   static final int TRIANGLESTRIPARRAY = 25;
   static final int VERTEXARRAY = 26;
   static final int VERTEXBUFFER = 27;
   static final int WORLD = 28;
   static final int SERIALIZEOUT = 29;
   static final int UNKNOWN = 30;
   static final int OBJECT3D_FID = 0;
   static final int GRAPHICS3D_FID = 1;
   static final int LOADER_FID = 2;
   static final int RAYINTERSECTION_FID = 3;
   static final int TRANSFORM_FID = 4;
   static final int SERIALIZEOUT_FID = 5;
   static Hashtable peerTable = new Hashtable();
   static Object3D[] XOT = new Object3D[0];
   static int XOTlength = 0;
   private static Object3D[] tmpXOT = null;
   private static boolean cleanPeerTable = false;
   private static Key lookup = new Key(0);

   static native int getVersionMajor();

   static native int getVersionMinor();

   static native int getRevisionMajor();

   static native int getRevisionMinor();

   static native int getBranchNumber();

   static native void releaseHandle(int var0);

   static native int getHandleType(int var0);

   static native int getHandleSize(int var0);

   static Object instantiateJavaPeer(int var0) {
      if(var0 == 0) {
         return null;
      } else {
         boolean var1 = false;
         boolean var11 = false;

         Object var3;
         try {
            var11 = true;
            Object var2;
            if((var2 = getJavaPeer(var0)) == null) {
               switch(getHandleType(var0)) {
               case 0:
                  var2 = new AnimationController(var0);
                  break;
               case 1:
                  var2 = new AnimationTrack(var0);
                  break;
               case 2:
                  var2 = new Appearance(var0);
                  break;
               case 3:
                  var2 = new Background(var0);
                  break;
               case 4:
                  var2 = new Camera(var0);
                  break;
               case 5:
                  var2 = new CompositingMode(var0);
                  break;
               case 6:
               case 10:
               case 17:
               case 22:
                  var2 = new Image2D(var0);
                  break;
               case 7:
                  var2 = new Fog(var0);
                  break;
               case 8:
                  var2 = new Graphics3D(var0);
                  break;
               case 9:
                  var2 = new Group(var0);
                  break;
               case 11:
                  var2 = new KeyframeSequence(var0);
                  break;
               case 12:
                  var2 = new Light(var0);
                  break;
               case 13:
                  var2 = new Loader(var0);
                  break;
               case 14:
                  var2 = new Material(var0);
                  break;
               case 15:
                  var2 = new Mesh(var0);
                  break;
               case 16:
                  var2 = new MorphingMesh(var0);
                  break;
               case 18:
                  var2 = new PolygonMode(var0);
                  break;
               case 19:
                  var2 = new RayIntersection(var0);
                  break;
               case 20:
                  var2 = new SkinnedMesh(var0);
                  break;
               case 21:
                  var2 = new Sprite3D(var0);
                  break;
               case 23:
                  var2 = new Texture2D(var0);
                  break;
               case 24:
                  var2 = new Transform(var0);
                  break;
               case 25:
                  var2 = new TriangleStripArray(var0);
                  break;
               case 26:
                  var2 = new VertexArray(var0);
                  break;
               case 27:
                  var2 = new VertexBuffer(var0);
                  break;
               case 28:
                  var2 = new World(var0);
                  break;
               default:
                  throw new IllegalArgumentException();
               }

               if(var2 instanceof Object3D) {
                  Object3D var13 = (Object3D)var2;
                  Hashtable var4 = null;

                  int var6;
                  for(int var5 = 0; (var6 = var13.getUserParameterValue(var5, (byte[])null)) != -1; ++var5) {
                     if(var4 == null) {
                        var4 = new Hashtable();
                     }

                     Integer var7 = new Integer(var13.getUserParameterID(var5));
                     byte[] var8 = new byte[var6];
                     var13.getUserParameterValue(var5, var8);
                     var4.put(var7, var8);
                  }

                  if(var4 != null) {
                     var13.removeUserParameters();
                     var13.setUserObject(var4);
                  }
               }

               addJavaPeer(var0, var2);
               var1 = true;
               return var2;
            }

            var3 = var2;
            var11 = false;
         } finally {
            if(var11) {
               if(!var1) {
                  releaseHandle(var0);
               }

            }
         }

         releaseHandle(var0);
         return var3;
      }
   }

   static void addJavaPeer(int var0, Object var1) {
      Hashtable var2 = peerTable;
      synchronized(peerTable) {
         if(cleanPeerTable) {
            Enumeration var3 = peerTable.keys();

            while(var3.hasMoreElements()) {
               Key var4 = (Key)var3.nextElement();
               if(((WeakReference)peerTable.get(var4)).get() == null) {
                  peerTable.remove(var4);
               }
            }

            cleanPeerTable = false;
         }

         peerTable.put(new Key(var0), new WeakReference(var1));
      }
   }

   static Object getJavaPeer(int var0) {
      Hashtable var1 = peerTable;
      synchronized(peerTable) {
         if(cleanPeerTable) {
            Enumeration var2 = peerTable.keys();

            while(var2.hasMoreElements()) {
               Key var3 = (Key)var2.nextElement();
               if(((WeakReference)peerTable.get(var3)).get() == null) {
                  peerTable.remove(var3);
               }
            }

            cleanPeerTable = false;
         }

         lookup.setKey(var0);
         Object var5;
         if((var5 = peerTable.get(lookup)) != null) {
            var5 = ((WeakReference)var5).get();
         }

         return var5;
      }
   }

   static int[] getJavaPeerArrayHandles(Object[] var0) {
      if(var0 == null) {
         return null;
      } else {
         synchronized(var0) {
            int[] var2 = new int[var0.length];

            for(int var3 = 0; var3 < var0.length; ++var3) {
               if(var0[var3] == null) {
                  var2[var3] = 0;
               } else {
                  if(!(var0[var3] instanceof Object3D)) {
                     throw new IllegalArgumentException(var0[var3] + "is not instanceof Object3D");
                  }

                  var2[var3] = ((Object3D)var0[var3]).swerveHandle;
               }
            }

            return var2;
         }
      }
   }

   static native void cacheFID(Class var0, int var1);

   static void addXOT(Object3D var0) {
      if(var0 != null && (var0.ii || var0.uo != null)) {
         Object3D[] var1 = XOT;
         synchronized(XOT) {
            for(int var2 = 0; var2 < XOTlength; ++var2) {
               if(XOT[var2] == var0) {
                  return;
               }
            }

            if(XOTlength == XOT.length) {
               tmpXOT = new Object3D[XOT.length == 0?2:XOT.length * 2];
               System.arraycopy(XOT, 0, tmpXOT, 0, XOT.length);
               XOT = tmpXOT;
               tmpXOT = null;
            }

            XOT[XOTlength++] = var0;
         }
      }
   }

   static void addXOT(Object3D[] var0) {
      if(var0 != null) {
         for(int var1 = 0; var1 < var0.length; ++var1) {
            addXOT(var0[var1]);
         }
      }

   }
}

/*
class Engine
{
    static final int ANIMATIONCONTROLLER = 0;
    static final int ANIMATIONTRACK = 1;
    static final int APPEARANCE = 2;
    static final int BACKGROUND = 3;
    static final int CAMERA = 4;
    static final int COMPOSITINGMODE = 5;
    static final int COMPRESSEDIMAGE2D = 6;
    static final int FOG = 7;
    static final int GRAPHICS3D = 8;
    static final int GROUP = 9;
    static final int IMAGE2D = 10;
    static final int KEYFRAMESEQUENCE = 11;
    static final int LIGHT = 12;
    static final int LOADER = 13;
    static final int MATERIAL = 14;
    static final int MESH = 15;
    static final int MORPHINGMESH = 16;
    static final int PLASMAIMAGE = 17;
    static final int POLYGONMODE = 18;
    static final int RAYINTERSECTION = 19;
    static final int SKINNEDMESH = 20;
    static final int SPRITE3D = 21;
    static final int STAGESET = 22;
    static final int TEXTURE2D = 23;
    static final int TRANSFORM = 24;
    static final int TRIANGLESTRIPARRAY = 25;
    static final int VERTEXARRAY = 26;
    static final int VERTEXBUFFER = 27;
    static final int WORLD = 28;
    static final int SERIALIZEOUT = 29;
    static final int UNKNOWN = 30;
    static final int OBJECT3D_FID = 0;
    static final int GRAPHICS3D_FID = 1;
    static final int LOADER_FID = 2;
    static final int RAYINTERSECTION_FID = 3;
    static final int TRANSFORM_FID = 4;
    static final int SERIALIZEOUT_FID = 5;
    static Hashtable peerTable;
    static Object3D[] XOT;
    static int XOTlength;
    private static Object3D[] tmpXOT;
    private static boolean cleanPeerTable;
    private static Key lookup;
    
    Engine() {
        super();
    }
    
    static native int getVersionMajor();
    
    static native int getVersionMinor();
    
    static native int getRevisionMajor();
    
    static native int getRevisionMinor();
    
    static native int getBranchNumber();
    
    static native void releaseHandle(final int p0);
    
    static native int getHandleType(final int p0);
    
    static native int getHandleSize(final int p0);
    
    static Object instantiateJavaPeer(final int n) {
        if (n == 0) {
            return null;
        }
        boolean b = false;
        try {
            final Object javaPeer;
            if ((javaPeer = getJavaPeer(n)) != null) {
                final Object o = javaPeer;
                releaseHandle(n);
                return o;
            }
            Object o2 = null;
            switch (getHandleType(n)) {
                case 0: {
                    o2 = new AnimationController(n);
                    break;
                }
                case 1: {
                    o2 = new AnimationTrack(n);
                    break;
                }
                case 2: {
                    o2 = new Appearance(n);
                    break;
                }
                case 3: {
                    o2 = new Background(n);
                    break;
                }
                case 4: {
                    o2 = new Camera(n);
                    break;
                }
                case 5: {
                    o2 = new CompositingMode(n);
                    break;
                }
                case 7: {
                    o2 = new Fog(n);
                    break;
                }
                case 8: {
                    o2 = new Graphics3D(n);
                    break;
                }
                case 9: {
                    o2 = new Group(n);
                    break;
                }
                case 6:
                case 10:
                case 17:
                case 22: {
                    o2 = new Image2D(n);
                    break;
                }
                case 11: {
                    o2 = new KeyframeSequence(n);
                    break;
                }
                case 12: {
                    o2 = new Light(n);
                    break;
                }
                case 13: {
                    o2 = new Loader(n);
                    break;
                }
                case 14: {
                    o2 = new Material(n);
                    break;
                }
                case 15: {
                    o2 = new Mesh(n);
                    break;
                }
                case 16: {
                    o2 = new MorphingMesh(n);
                    break;
                }
                case 18: {
                    o2 = new PolygonMode(n);
                    break;
                }
                case 19: {
                    o2 = new RayIntersection(n);
                    break;
                }
                case 20: {
                    o2 = new SkinnedMesh(n);
                    break;
                }
                case 21: {
                    o2 = new Sprite3D(n);
                    break;
                }
                case 23: {
                    o2 = new Texture2D(n);
                    break;
                }
                case 24: {
                    o2 = new Transform(n);
                    break;
                }
                case 25: {
                    o2 = new TriangleStripArray(n);
                    break;
                }
                case 26: {
                    o2 = new VertexArray(n);
                    break;
                }
                case 27: {
                    o2 = new VertexBuffer(n);
                    break;
                }
                case 28: {
                    o2 = new World(n);
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            if (o2 instanceof Object3D) {
                final AnimationController animationController = (AnimationController)o2;
                Hashtable<Integer, byte[]> userObject = null;
                int userParameterValue;
                for (int n2 = 0; (userParameterValue = animationController.getUserParameterValue(n2, null)) != -1; ++n2) {
                    if (userObject == null) {
                        userObject = new Hashtable<Integer, byte[]>();
                    }
                    final Integer n3 = new Integer(animationController.getUserParameterID(n2));
                    final byte[] array = new byte[userParameterValue];
                    animationController.getUserParameterValue(n2, array);
                    userObject.put(n3, array);
                }
                if (userObject != null) {
                    animationController.removeUserParameters();
                    animationController.setUserObject(userObject);
                }
            }
            addJavaPeer(n, o2);
            b = true;
            return o2;
        }
        finally {
            if (!b) {
                releaseHandle(n);
            }
        }
    }
    
    static void addJavaPeer(final int n, final Object o) {
        synchronized (Engine.peerTable) {
            if (Engine.cleanPeerTable) {
                final Enumeration<Key> keys = Engine.peerTable.keys();
                while (keys.hasMoreElements()) {
                    final Key key = keys.nextElement();
                    if (((WeakReference)Engine.peerTable.get(key)).get() == null) {
                        Engine.peerTable.remove(key);
                    }
                }
                Engine.cleanPeerTable = false;
            }
            Engine.peerTable.put(new Key(n), new WeakReference<Object>(o));
        }
    }
    
    static Object getJavaPeer(final int key) {
        Object o;
        synchronized (Engine.peerTable) {
            if (Engine.cleanPeerTable) {
                final Enumeration<Key> keys = Engine.peerTable.keys();
                while (keys.hasMoreElements()) {
                    final Key key2 = keys.nextElement();
                    if (((WeakReference)Engine.peerTable.get(key2)).get() == null) {
                        Engine.peerTable.remove(key2);
                    }
                }
                Engine.cleanPeerTable = false;
            }
            Engine.lookup.setKey(key);
            if ((o = Engine.peerTable.get(Engine.lookup)) != null) {
                o = ((WeakReference<Object>)o).get();
            }
        }
        return o;
    }
    
    static int[] getJavaPeerArrayHandles(final Object[] array) {
        if (array == null) {
            return null;
        }
        final int[] array2;
        synchronized (array) {
            array2 = new int[array.length];
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == null) {
                    array2[i] = 0;
                }
                else {
                    if (!(array[i] instanceof Object3D)) {
                        throw new IllegalArgumentException(array[i] + "is not instanceof Object3D");
                    }
                    array2[i] = ((Object3D)array[i]).swerveHandle;
                }
            }
        }
        return array2;
    }
    
    static native void cacheFID(final Class p0, final int p1);
    
    static void addXOT(final Object3D object3D) {
        if (object3D != null && (object3D.ii || object3D.uo != null)) {
            synchronized (Engine.XOT) {
                for (int i = 0; i < Engine.XOTlength; ++i) {
                    if (Engine.XOT[i] == object3D) {
                        return;
                    }
                }
                if (Engine.XOTlength == Engine.XOT.length) {
                    Engine.tmpXOT = new Object3D[(Engine.XOT.length == 0) ? 2 : (Engine.XOT.length * 2)];
                    System.arraycopy(Engine.XOT, 0, Engine.tmpXOT, 0, Engine.XOT.length);
                    Engine.XOT = Engine.tmpXOT;
                    Engine.tmpXOT = null;
                }
                Engine.XOT[Engine.XOTlength++] = object3D;
            }
        }
    }
    
    static void addXOT(final Object3D[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                addXOT(array[i]);
            }
        }
    }
    
    static {
        Engine.peerTable = new Hashtable();
        Engine.XOT = new Object3D[0];
        Engine.XOTlength = 0;
        Engine.tmpXOT = null;
        Engine.cleanPeerTable = false;
        Engine.lookup = new Key(0);
    }
}
*/
