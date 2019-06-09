package lab;

import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.swing.JFrame;
import javax.xml.crypto.dsig.Transform;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;

import java.awt.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class Main extends JFrame
{
    public Canvas3D myCanvas3D;

    public Main() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);

        simpUniv.getViewingPlatform().setNominalViewingTransform();

        createSceneGraph(simpUniv);

        addLight(simpUniv);

        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        setTitle("Sample");
        setSize(700, 700);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        new Main();
    }

    public void createSceneGraph(SimpleUniverse su)
    {

        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        Scene widowScene = null;
        try
        {
            widowScene = f.load("models/black_widow.obj");
        }
        catch (Exception e)
        {
            System.out.println("File loading failed:" + e);
        }

        Transform3D scaling = new Transform3D();
        scaling.setScale(1.0/6);
        Transform3D tfWidow = new Transform3D();
        tfWidow.rotX(Math.PI/3);
        tfWidow.mul(scaling);
        TransformGroup tgWidow = new TransformGroup(tfWidow);
        TransformGroup sceneGroup = new TransformGroup();


        Hashtable widowNamedObjects = widowScene.getNamedObjects();
        Enumeration enumer = widowNamedObjects.keys();
        String name;
        while (enumer.hasMoreElements())
        {
            name = (String) enumer.nextElement();
            System.out.println("Name: "+name);
        }

        Shape3D leg1 = (Shape3D) widowNamedObjects.get("leg1");
        Shape3D leg2 = (Shape3D) widowNamedObjects.get("leg2");
        Shape3D leg3 = (Shape3D) widowNamedObjects.get("leg3");
        Shape3D leg4 = (Shape3D) widowNamedObjects.get("leg4");
        Shape3D leg5 = (Shape3D) widowNamedObjects.get("leg5");
        Shape3D leg6 = (Shape3D) widowNamedObjects.get("leg6");
        Shape3D leg7 = (Shape3D) widowNamedObjects.get("leg7");
        Shape3D leg8 = (Shape3D) widowNamedObjects.get("leg8");

        Shape3D body = (Shape3D) widowNamedObjects.get("blkw_body");

        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        TransformGroup transformGroup = new TransformGroup();
        transformGroup.addChild(body.cloneTree());


        TransformGroup leg1Gr = new TransformGroup();
        TransformGroup leg2Gr = new TransformGroup();
        TransformGroup leg3Gr = new TransformGroup();
        TransformGroup leg4Gr = new TransformGroup();
        TransformGroup leg5Gr = new TransformGroup();
        TransformGroup leg6Gr = new TransformGroup();
        TransformGroup leg7Gr = new TransformGroup();
        TransformGroup leg8Gr = new TransformGroup();


        leg1Gr.addChild(leg1.cloneTree());
        leg2Gr.addChild(leg2.cloneTree());
        leg3Gr.addChild(leg3.cloneTree());
        leg4Gr.addChild(leg4.cloneTree());
        leg5Gr.addChild(leg5.cloneTree());
        leg6Gr.addChild(leg6.cloneTree());
        leg7Gr.addChild(leg7.cloneTree());
        leg8Gr.addChild(leg8.cloneTree());


        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0),Double.MAX_VALUE);
        BranchGroup theScene = new BranchGroup();
        Transform3D tCrawl = new Transform3D();
        Transform3D tCrawl1 = new Transform3D();
        tCrawl.rotY(120D);
        tCrawl1.rotX(90D);
        long crawlTime = 10000;
        Alpha crawlAlpha = new Alpha(1,
                Alpha.INCREASING_ENABLE,
                0,
                0, crawlTime,0,0,0,0,0);
        float crawlDistance = 3.0f;
        PositionInterpolator posICrawl = new PositionInterpolator(crawlAlpha,
                sceneGroup,tCrawl, -9.0f, crawlDistance);

        long crawlTime1 = 30000;
        Alpha crawlAlpha1 = new Alpha(1,
                Alpha.INCREASING_ENABLE,
                3000,
                0, crawlTime1,0,0,0,0,0);
        float crawlDistance1 = 15.0f;
        PositionInterpolator posICrawl1 = new PositionInterpolator(crawlAlpha1,
                sceneGroup,tCrawl1, -9.0f, crawlDistance1);


        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
        posICrawl.setSchedulingBounds(bs);
        posICrawl1.setSchedulingBounds(bs);
        sceneGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneGroup.addChild(posICrawl);


        int timeStart = 500;
        int timeRotationHour = 500;

        Transform3D leg1RotationAxis = new Transform3D();
        leg1RotationAxis.rotZ(Math.PI / 2);
        Alpha leg1RotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, timeStart, 0,
                timeRotationHour, 0, 0, timeRotationHour, 0, 0);
        RotationInterpolator leg1Rotation = new RotationInterpolator(leg1RotationAlpha, leg1Gr,
                leg1RotationAxis, (float) Math.PI / 4, 0.0f);
        leg1Rotation.setSchedulingBounds(bounds);

        RotationInterpolator leg3Rotation = new RotationInterpolator(leg1RotationAlpha, leg3Gr,
                leg1RotationAxis, (float) Math.PI / 4, 0.0f);
        leg3Rotation.setSchedulingBounds(bounds);

        RotationInterpolator leg5Rotation = new RotationInterpolator(leg1RotationAlpha, leg5Gr,
                leg1RotationAxis, (float) Math.PI / 4, 0.0f);
        leg5Rotation.setSchedulingBounds(bounds);

        RotationInterpolator leg7Rotation = new RotationInterpolator(leg1RotationAlpha, leg7Gr,
                leg1RotationAxis, (float) Math.PI / 4, 0.0f);
        leg7Rotation.setSchedulingBounds(bounds);



        Transform3D leg2RotationAxis = new Transform3D();
        leg2RotationAxis.rotZ(Math.PI / 2);
        Alpha leg2RotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE, 0, 0,
                timeRotationHour, 0, 0, timeRotationHour, 0, 0);
        RotationInterpolator leg2Rotation = new RotationInterpolator(leg2RotationAlpha, leg2Gr,
                leg2RotationAxis, (float) Math.PI / 4, 0.0f);
        leg2Rotation.setSchedulingBounds(bounds);

        RotationInterpolator leg4Rotation = new RotationInterpolator(leg2RotationAlpha, leg4Gr,
                leg2RotationAxis, (float) Math.PI / 4, 0.0f);
        leg4Rotation.setSchedulingBounds(bounds);

        RotationInterpolator leg6Rotation = new RotationInterpolator(leg2RotationAlpha, leg6Gr,
                leg2RotationAxis, (float) Math.PI / 4, 0.0f);
        leg6Rotation.setSchedulingBounds(bounds);

        RotationInterpolator leg8Rotation = new RotationInterpolator(leg2RotationAlpha, leg8Gr,
                leg2RotationAxis, (float) Math.PI / 4, 0.0f);
        leg8Rotation.setSchedulingBounds(bounds);




        leg1Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leg2Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leg3Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leg4Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leg5Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leg6Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leg7Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leg8Gr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        leg1Gr.addChild(leg1Rotation);
        leg2Gr.addChild(leg2Rotation);
        leg3Gr.addChild(leg3Rotation);
        leg4Gr.addChild(leg4Rotation);
        leg5Gr.addChild(leg5Rotation);
        leg6Gr.addChild(leg6Rotation);
        leg7Gr.addChild(leg7Rotation);
        leg8Gr.addChild(leg8Rotation);


        sceneGroup.addChild(transformGroup);
        sceneGroup.addChild(leg1Gr);
        sceneGroup.addChild(leg2Gr);
        sceneGroup.addChild(leg3Gr);
        sceneGroup.addChild(leg4Gr);
        sceneGroup.addChild(leg5Gr);
        sceneGroup.addChild(leg6Gr);
        sceneGroup.addChild(leg7Gr);
        sceneGroup.addChild(leg8Gr);
        tgWidow.addChild(sceneGroup);
        theScene.addChild(tgWidow);

        Background bg = new Background(new Color3f(1f,1f,0.5f));

        bg.setApplicationBounds(bounds);
        theScene.addChild(bg);
        theScene.compile();

        su.addBranchGraph(theScene);
    }


    public void addLight(SimpleUniverse su)
    {
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(1f,0,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }
}

