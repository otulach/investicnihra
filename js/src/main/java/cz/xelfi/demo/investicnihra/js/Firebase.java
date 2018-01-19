package cz.xelfi.demo.investicnihra.js;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import net.java.html.BrwsrCtx;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import net.java.html.json.Model;
import net.java.html.json.Models;
import net.java.html.json.Property;

@JavaScriptResource("firebase-4.8.1.js")
@Model(className = "FirebaseConfig", builder = "use", properties = {
    @Property(name = "apiKey", type = String.class, mutable = false),
    @Property(name = "authDomain", type = String.class, mutable = false),
    @Property(name = "databaseURL", type = String.class, mutable = false),
    @Property(name = "projectId", type = String.class, mutable = false),
    @Property(name = "storageBucket", type = String.class, mutable = false),
    @Property(name = "messagingSenderId", type = String.class, mutable = false),
})
public final class Firebase {
    private final Object db;

    private Firebase(Object obj) {
        this.db = obj;
    }

    public static Firebase create(FirebaseConfig config) {
        Object c = Models.toRaw(config);
        Object obj = initializeApp0(c);
        return new Firebase(obj);
    }

    public <T> Ref<T> ref(Class<T> type, String path) {
        return new Ref<>(type, ref0(db, path));
    }

    public static class Ref<T> {
        private final Class<T> type;
        private final Object js;

        Ref(Class<T> type, Object js) {
            this.type = type;
            this.js = js;
        }

        public void set(List<T> json) {
            call0(js, "set", json.toString());
        }

        public void on(List<T> update) {
            request0(js, "on", type, update);
        }

        public void once(List<T> update) {
            request0(js, "once", type, update);
        }

        public void off() {
            call0(js, "off", null);
        }
    }

    @JavaScriptBody(args = "o", body =
        "firebase.initializeApp(o);\n" +
        "return firebase.database();\n"
    )
    static native Object initializeApp0(Object o);

    @JavaScriptBody(args = { "db", "path" }, body =
        "return db.ref(path);\n"
    )
    static native Object ref0(Object db, String path);

    @JavaScriptBody(args = { "ref", "what", "data" }, body =
        "ref[what](data)"
    )
    static native void call0(Object ref, String what, Object data);

    @JavaScriptBody(javacall = true, args = { "ref", "what", "type", "data" }, body =
        "ref[what]('value', function(s) {\n" +
        "  @cz.xelfi.demo.investicnihra.js.Firebase::back0" +
            "(Ljava/lang/Class;Ljava/util/List;Ljava/lang/String;)" +
        "  )(type, data, s.val().toString());\n" +
        "})\n"
    )
    static native void request0(Object ref, String what, Class<?> type, List<?> data);

    static <T> void back0(Class<T> type, List<T> data, String raw) throws IOException {
        try (ByteArrayInputStream is = new ByteArrayInputStream(raw.getBytes("UTF-8"))) {
            BrwsrCtx ctx = BrwsrCtx.findDefault(type);
            data.clear();
            Models.parse(ctx, type, is, data);
        }
    }
}
