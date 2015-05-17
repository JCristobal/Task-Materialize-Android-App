package jcristobal.task_materialize_android_app;

import java.util.List;

public interface GetMemesCallback {

        public void onMemesResult (List<MemeEntity> memesList);

        public void onMemesError ();
    }