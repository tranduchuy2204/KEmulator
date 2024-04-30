package emulator.graphics3D.m3g;

import java.util.Vector;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.SkinnedMesh;
import javax.microedition.m3g.Sprite3D;
import javax.microedition.m3g.Transform;

public final class RenderPipe {
    private static RenderPipe inst;
    private static RenderPipe viewInst;

    private Node parentNode;
    private Vector roList = new Vector();

    public static RenderPipe getInstance() {
        if (inst == null) {
            inst = new RenderPipe();
        }

        return inst;
    }

    public static RenderPipe getViewInstance() {
        if (viewInst == null) {
            viewInst = new RenderPipe();
        }

        return viewInst;
    }

    public final int getSize() {
        return roList.size();
    }

    public final void clear() {
        roList.clear();
        parentNode = null;
    }

    public final RenderObject getRenderObj(int index) {
        return (RenderObject) roList.get(index);
    }

    public final boolean isVisible(Node node) {
        Node tmpNode = node;

        while (true) {
            if (tmpNode == null) break;

            if (!tmpNode.isRenderingEnabled()) {
                return false;
            }

            if (parentNode == null || tmpNode == parentNode) {
                break;
            }

            tmpNode = tmpNode.getParent();
        }

        return true;
    }

    public final float getEffectiveAlphaFactor(Node node) {
        float alphaFactor = 1;
        Node tmpNode = node;

        while (true) {
            alphaFactor *= tmpNode.getAlphaFactor();

            if (tmpNode.getParent() == null || node == parentNode) {
                return alphaFactor;
            }

            tmpNode = tmpNode.getParent();
        }
    }

    public final void pushRenderNode(Node node, Transform trans) {
        this.parentNode = node;
        this.pushRenderNodeImpl(node, trans);
    }

    private void pushRenderNodeImpl(Node node, Transform trans) {
        if (isVisible(node)) {
            if (trans == null) {
                trans = new Transform();
            }

            if (node instanceof Sprite3D) {
                Sprite3D spr = (Sprite3D) node;

                if (spr.getAppearance() != null && spr.getCropWidth() != 0 && spr.getCropHeight() != 0) {
                    insertNodeInList(new RenderObject(node, trans, 0, this));
                }
            } else {
                if (node instanceof Mesh) {
                    int submeshes = ((Mesh) node).getSubmeshCount();

                    for (int i = 0; i < submeshes; ++i) {
                        if (((Mesh) node).getAppearance(i) != null) {
                            insertNodeInList(new RenderObject(node, trans, i, this));
                        }
                    }

                    if (node instanceof SkinnedMesh) {
                        Group skeleton = ((SkinnedMesh) node).getSkeleton();

                        Transform skeletonMat = new Transform();
                        skeleton.getCompositeTransform(skeletonMat);

                        Transform tmpMat = new Transform(trans);
                        tmpMat.postMultiply(skeletonMat);

                        pushRenderNodeImpl(skeleton, tmpMat);
                    }
                } else if (node instanceof Group) {
                    Group group = (Group) node;

                    Transform childTrans = new Transform();
                    Transform groupTrans = new Transform(trans);

                    for (int i = 0; i < group.getChildCount(); ++i) {
                        Node child = group.getChild(i);

                        child.getCompositeTransform(childTrans);
                        childTrans.preMultiply(groupTrans);

                        pushRenderNodeImpl(child, childTrans);
                    }
                }

            }
        }
    }

    private void insertNodeInList(RenderObject ro) {
        int index;

        for (index = 0; index < roList.size(); index++) {
            RenderObject ro2 = (RenderObject) roList.get(index);
            if(ro2.sortKey >= ro.sortKey) break;
        }

        roList.insertElementAt(ro, index);
    }
}