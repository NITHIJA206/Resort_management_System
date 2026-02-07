import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default function DashboardPage() {
    return (
        <div className="space-y-8">
            <h1 className="text-4xl font-bold text-white gradient-text">
                Welcome to Dashboard
            </h1>

            <Card className="glass">
                <CardHeader>
                    <CardTitle className="text-2xl text-white">Quick Overview</CardTitle>
                </CardHeader>
                <CardContent>
                    <p className="text-lg text-white/80">
                        Select a section from the top navigation to manage rooms, bookings, payments, or users.
                    </p>
                </CardContent>
            </Card>
        </div>
    )
}