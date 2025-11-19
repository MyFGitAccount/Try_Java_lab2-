use actix_web::{get, post, web, App, HttpResponse, HttpServer, Responder};

#[get("/helloworld")]
async fn hello()->impl Responder{
   HttpResponse::Ok().body("Hello World")
}

async fn index()->impl Responder{
   "Hi this is a index"
}

#[post("/echo")]
async fn echo(req_body: String) -> impl Responder {
   HttpResponse::Ok().body(req_body)
}

async fn manual_hello()-> impl Responder{
   HttpResponse::Ok().body("Hey there!Mother fucker")
}

async fn fuck_you()->impl Responder{
   HttpResponse::Ok().body("Hi fuck you")
}

struct FuckU{
  name: String
}

#[get("/fuckfuck")]
async fn fuckfuck(fucksomeone: web::Data<FuckU>) -> String {
    let fuck_who = &fucksomeone.name; // <- get app_name
    format!("Fuck you {fuck_who}!") // <- response with app_name
}
#[actix_web::main]
async fn main() -> std::io::Result<()>{
    HttpServer::new(||{
       App::new()
          .service(
           web::scope("/app")
           .route("/index.html",web::get().to(index))
           .route("/fuckU.html",web::get().to(fuck_you))
          )
          .app_data(web::Data::new(FuckU{
             name: String::from("Adam")
          }))
          .service(fuckfuck)
          .service(hello)
          .service(echo)
          .route("/hey",web::get().to(manual_hello))
       })
       .bind(("127.0.0.1",8080))?
       .run()
       .await
}
