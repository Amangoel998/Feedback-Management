export interface LoginResponse{
    userid: String;
    user: String;
    email: String;
    role: String;
}
export interface LoginRequest{
    id: String;
    password: String;
}