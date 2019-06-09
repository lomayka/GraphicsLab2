package lab;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.text.StyleConstants.Background;

public class Dumbbell implements ActionListener {
    private float upperEyeLimit = 15.0f;
    private float lowerEyeLimit = 5.0f;
    private float farthestEyeLimit = 28.0f;
    private float nearestEyeLimit = 22.0f;

    private TransformGroup treeTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new Dumbbell();
    }

    private Dumbbell() {
        Timer timer = new Timer(50, this);
        SimpleUniverse universe = new SimpleUniverse();

        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        timer.start();
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        buildDumbbell();
        objRoot.addChild(treeTransformGroup);

        Background background = new Background(new Color3f(0, 0.9f, 0.9f)); // white color
        BoundingSphere sphere = new BoundingSphere(new Point3d(100,1000,0), 1000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0, 0);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }

    private TransformGroup getTransformGroup(Vector3f translation){
        Transform3D weightT = new Transform3D();
        TransformGroup weightTG = new TransformGroup();
        weightT.setTranslation(translation);
        weightTG.setTransform(weightT);
        return weightTG;
    }
    private void buildDumbbell() {
        //small dumbbell
        Cylinder stick1 = new Cylinder(1, 12, Utils.getDumbbellAppearence());
        Cylinder weight1 = new Cylinder(4, 1, Utils.getDumbbellAppearence());
        Cylinder weight2 = new Cylinder(4, 1, Utils.getDumbbellAppearence());
        Transform3D stick1T = new Transform3D();
        TransformGroup stick1TG = new TransformGroup();
        stick1TG.setTransform(stick1T);
        stick1TG.addChild(stick1);


        TransformGroup weight1TG = getTransformGroup(new Vector3f(0, 4f, 0));
        weight1TG.addChild(weight1);
        stick1TG.addChild(weight1TG);

        TransformGroup weight2TG = getTransformGroup(new Vector3f(0, -4f, 0));
        weight2TG.addChild(weight2);
        stick1TG.addChild(weight2TG);


        // big
        Cylinder stick2 = new Cylinder(1, 16, Utils.getDumbbellAppearence());
        Cylinder weight3 = new Cylinder(4, 1, Utils.getDumbbellAppearence());
        Cylinder weight4 = new Cylinder(4, 1, Utils.getDumbbellAppearence());
        Cylinder weight5 = new Cylinder(3, 1, Utils.getDumbbellAppearence());
        Cylinder weight6 = new Cylinder(3, 1, Utils.getDumbbellAppearence());
        Transform3D stick2T = new Transform3D();
        TransformGroup stick2TG = new TransformGroup();
        stick2T.setTranslation(new Vector3f(10, 0,0 ));
        stick2TG.setTransform(stick2T);
        stick2TG.addChild(stick2);

        TransformGroup weight3TG = getTransformGroup(new Vector3f(0, 4f, 0));
        weight3TG.addChild(weight3);
        stick2TG.addChild(weight3TG);

        TransformGroup weight4TG = getTransformGroup(new Vector3f(0, -4f, 0));
        weight4TG.addChild(weight4);
        stick2TG.addChild(weight4TG);

        TransformGroup weight5TG = getTransformGroup(new Vector3f(0, -5f, 0));
        weight5TG.addChild(weight5);
        stick2TG.addChild(weight5TG);

        TransformGroup weight6TG = getTransformGroup(new Vector3f(0, 5f, 0));
        weight6TG.addChild(weight6);
        stick2TG.addChild(weight6TG);

        //large
        Cylinder stick3 = new Cylinder(1, 18, Utils.getDumbbellAppearence());
        Cylinder weight7 = new Cylinder(5, 1, Utils.getDumbbellAppearence());
        Cylinder weight8 = new Cylinder(5, 1, Utils.getDumbbellAppearence());
        Cylinder weight9 = new Cylinder(4, 1, Utils.getDumbbellAppearence());
        Cylinder weight10 = new Cylinder(4, 1, Utils.getDumbbellAppearence());
        Cylinder weight11 = new Cylinder(3, 1, Utils.getDumbbellAppearence());
        Cylinder weight12 = new Cylinder(3, 1, Utils.getDumbbellAppearence());

        Transform3D stick3T = new Transform3D();
        TransformGroup stick3TG = new TransformGroup();
        stick3T.setTranslation(new Vector3f(-10, 0,0 ));
        stick3TG.setTransform(stick3T);
        stick3TG.addChild(stick3);

        TransformGroup weight7TG = getTransformGroup(new Vector3f(0, 4f, 0));
        weight7TG.addChild(weight7);
        stick3TG.addChild(weight7TG);

        TransformGroup weight8TG = getTransformGroup(new Vector3f(0, -4f, 0));
        weight8TG.addChild(weight8);
        stick3TG.addChild(weight8TG);

        TransformGroup weight9TG = getTransformGroup(new Vector3f(0, -5f, 0));
        weight9TG.addChild(weight9);
        stick3TG.addChild(weight9TG);

        TransformGroup weight10TG = getTransformGroup(new Vector3f(0, 5f, 0));
        weight10TG.addChild(weight10);
        stick3TG.addChild(weight10TG);

        TransformGroup weight11TG = getTransformGroup(new Vector3f(0, 6f, 0));
        weight11TG.addChild(weight11);
        stick3TG.addChild(weight11TG);

        TransformGroup weight12TG = getTransformGroup(new Vector3f(0, -6f, 0));
        weight12TG.addChild(weight12);
        stick3TG.addChild(weight12TG);


        treeTransformGroup.addChild(stick1TG);
        treeTransformGroup.addChild(stick2TG);
        treeTransformGroup.addChild(stick3TG);


    }

    // ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        float delta = 0.03f;

        // rotation of the castle
        treeTransform3D.rotZ(angle);
        treeTransformGroup.setTransform(treeTransform3D);
        angle += delta;

        // change of the camera position up and down within defined limits
        if (eyeHeight > upperEyeLimit){
            descend = true;
        }else if(eyeHeight < lowerEyeLimit){
            descend = false;
        }
        if (descend){
            eyeHeight -= delta;
        }else{
            eyeHeight += delta;
        }

        // change camera distance to the scene
        if (eyeDistance > farthestEyeLimit){
            approaching = true;
        }else if(eyeDistance < nearestEyeLimit){
            approaching = false;
        }
        if (approaching){
            eyeDistance -= delta;
        }else{
            eyeDistance += delta;
        }

        Point3d eye = new Point3d(eyeDistance, eyeDistance, eyeHeight); // spectator's eye
        Point3d center = new Point3d(.0f, .0f ,0.1f); // sight target
        Vector3d up = new Vector3d(.0f, .0f, 1.0f);; // the camera frustum
        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
    }
}
